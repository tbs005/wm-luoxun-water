package com.waimung.luoxun.water;

import java.nio.ByteOrder;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.waimung.luoxun.water.common.Decoder;
import com.waimung.luoxun.water.common.Encoder;
import com.waimung.luoxun.water.common.ProtocolHandler;
import com.waimung.luoxun.water.config.Config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class Server implements ApplicationRunner {
	private static final Logger log = LoggerFactory.getLogger(Server.class);
	@Autowired
	private Config config;
	private NioEventLoopGroup bossGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));
	private NioEventLoopGroup workGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));

	@PreDestroy
	public void shutDown() {
		// 关闭主线程
		bossGroup.shutdownGracefully();
		// 关闭工作线程
		workGroup.shutdownGracefully();
		System.out.println("NettyServer shutDown successfully.");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Start NettyServer...");
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.option(NioChannelOption.SO_BACKLOG, 1024);
		serverBootstrap.childOption(NioChannelOption.TCP_NODELAY, true);
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		serverBootstrap.handler(new LoggingHandler(LogLevel.DEBUG));
		try {
			serverBootstrap.group(bossGroup, workGroup);

            LoggingHandler debugLogHandler = new LoggingHandler(LogLevel.DEBUG);
            LoggingHandler infoLogHandler = new LoggingHandler(LogLevel.DEBUG);
            
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("debug", debugLogHandler);
					pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN,1054, 2, 1, 9, 0, true));
					pipeline.addLast("protocolDecoder", new Decoder());
					pipeline.addLast("protocolEncoder", new Encoder());
					
					pipeline.addLast("info", infoLogHandler);
					pipeline.addLast(new ProtocolHandler());
				}

			});
			
			ChannelFuture channelFuture = serverBootstrap.bind(config.getPort()).sync();
			log.info("Start NettyServer successfully, listening at port:{}", config.getPort());
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error("NettyServer start error:", e);
		} finally {
			//
		}
	}
}
