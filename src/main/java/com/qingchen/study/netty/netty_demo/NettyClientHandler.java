package com.qingchen.study.netty.netty_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.NettyRuntime;

/**
 * @ClassName NettyClientHandler
 * @description: 自定义个一个handler要继承ChannelInboundHandlerAdapter
 * @author: WangChen
 * @create: 2020-03-02 20:53
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {



    /**
     * 当通道就绪就会触发该方法.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server", CharsetUtil.UTF_8));
    }

    /**
     * 读取数据(服务端来得数据) 当通道有读取事件时触发
     * @param ctx ChannelHandlerContext上下文对象, 其中包含pipeline, channel
     * @param msg 客户端发送得数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("ctx = " + ctx);
        //ByteBuf是netty提供得 对nio得在封装, 效率更快
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址:"+ ctx.channel().remoteAddress());
    }


    /**
     * 捕获异常关闭
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
