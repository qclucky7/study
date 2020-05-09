package com.qingchen.study.netty.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @ClassName NettyClientHandler
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 16:40
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {

    private ChannelHandlerContext channelHandlerContext;
    private String result;
    private String para;

    public void setPara(String para) {
        this.para = para;
    }

    /**
     * 与服务连接就会被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = (String) msg;
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public synchronized Object call() throws Exception {
        channelHandlerContext.writeAndFlush(para);
        wait();
        return result;
    }
}
