package com.xtwy.netty.handler;



import com.alibaba.fastjson.JSONObject;
import com.xtwy.netty.client.DefaultFuture;
import com.xtwy.netty.util.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;


public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg.toString().equals("ping")) {
			ctx.channel().writeAndFlush("ping\r\n");
			return;
		}
//		System.out.println(msg.toString());
//		ctx.channel().attr(AttributeKey.valueOf("sssss")).set(msg);
		Response response = JSONObject.parseObject(msg.toString(),Response.class);
		System.out.println("接受服务器返回数据，"+ JSONObject.toJSONString(response));
		DefaultFuture.receive(response);
//		ctx.channel().close();//需要等通道关闭才能获取到数据
	}




}
