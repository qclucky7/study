package com.qingchen.study.netty.netty_chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName NettyServerHandler
 * @description:
 * @author: WangChen
 * @create: 2020-03-04 18:11
 **/
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    //都能实现  ChannelGroup是netty自带
    private static List<Channel> list = new ArrayList<>();

    //ChannelGroup是一个channel组    GlobalEventExecutor.INSTANCE是一个全局事件执行器
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 一旦连接被建立 就会执行这个方法 第一个被执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.add(channel);
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + "[客户端]" + channel.remoteAddress() + "加入聊天");


    }

    /**
     * 通道断开连接会执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + "[客户端]" + channel.remoteAddress() + "离开聊天");
        System.out.println("channelGroup size = " + channelGroup.size());


    }

    /**
     * 通道处于活跃状态就会执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        System.out.println(socketAddress + "上线");
    }


    /**
     * 通道不活跃会执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        System.out.println(socketAddress + "下线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {

        System.out.println("msg = " +  msg);

        Channel channel = channelHandlerContext.channel();

        channelGroup.forEach(channel1 -> {
            if (channel1 != channel){
                channel1.writeAndFlush(simpleDateFormat.format(new Date()) + "[客户端]" + channel.remoteAddress() + "消息 ：" + msg + "\n");
            } else {
                 channel.writeAndFlush(simpleDateFormat.format(new Date()) + "[自己发送了消息]" + msg + "\n");
            }
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
