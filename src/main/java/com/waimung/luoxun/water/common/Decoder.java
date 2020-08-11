package com.waimung.luoxun.water.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Decoder extends MessageToMessageDecoder<ByteBuf> {
	private static final Logger log = LoggerFactory.getLogger(Decoder.class);
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
		byte[] orgin = new byte[byteBuf.capacity()];
		byteBuf.getBytes(0, orgin);
		log.info("raw:[{}],len:{}",ByteUtil.hexString(orgin), byteBuf.capacity());
		RequestMessage requestMessage = new RequestMessage();
		requestMessage.decode(byteBuf);
		out.add(requestMessage);
	}

}
