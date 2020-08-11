package com.waimung.luoxun.water.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtocolHandler extends SimpleChannelInboundHandler<RequestMessage> {
	private static final Logger log = LoggerFactory.getLogger(ProtocolHandler.class);
	private static final Gson gson = new Gson();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RequestMessage requestMessage) throws Exception {
		log.info("requestMessage:{}",gson.toJson(requestMessage));
		Operation  command  = requestMessage.getMessageBody();
		if(null == command) {
			log.error("not writable now, message dropped");
			return;
		}
		OperationResult commandResult = command.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(commandResult);
        
        if (ctx.channel().isActive() && ctx.channel().isWritable()) {
            ctx.writeAndFlush(responseMessage);
        } else {
            log.error("not writable now, message dropped");
        }
		
	}

}
