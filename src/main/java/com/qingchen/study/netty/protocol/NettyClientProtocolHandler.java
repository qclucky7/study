package com.qingchen.study.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName NettyServerHandler
 * @description:
 * @author: WangChen
 * @create: 2020-03-04 18:11
 **/
public class NettyClientProtocolHandler extends SimpleChannelInboundHandler<DataProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DataProtocol msg) throws Exception {

        byte[] bytes = msg.getBytes();
        int length = msg.getLength();

        System.out.println();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
