package com.qingchen.study.netty.rpc.server;

import com.qingchen.study.netty.rpc.commonI.ServiceTestImp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName NettyServerHandler
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 17:43
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg = " + msg);
        if (msg.toString().startsWith("ServiceTest#sendMessage#")){
            String result = new ServiceTestImp().sendMessage(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
