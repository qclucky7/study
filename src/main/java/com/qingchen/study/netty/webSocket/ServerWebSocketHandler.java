package com.qingchen.study.netty.webSocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @ClassName ServerWebSocketHandler
 * @description: TextWebSocketFrame标识一个文本帧
 * @author: WangChen
 * @create: 2020-03-05 14:50
 **/
public class ServerWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("服务器收到消息");

        Channel channel = channelHandlerContext.channel();
        channel.writeAndFlush(new TextWebSocketFrame("服务器收到消息:" + textWebSocketFrame.text()));
    }

    /**
     * 浏览器连接就会调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //可以拿到通道唯一的id
        ctx.channel().id().asLongText();
        //唯一id 但是可以重复
        ctx.channel().id().asShortText();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //可以拿到通道唯一的id
        ctx.channel().id().asLongText();
        //唯一id 但是可以重复
        ctx.channel().id().asShortText();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
