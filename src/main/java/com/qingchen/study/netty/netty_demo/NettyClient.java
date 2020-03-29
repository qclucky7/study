package com.qingchen.study.netty.netty_demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName NettyClient
 * @description:
 * @author: WangChen
 * @create: 2020-03-02 21:22
 **/
public class NettyClient {

    public static void main(String[] args) throws Exception{

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new HttpClientInitializer());

            System.out.println("客户端启动...");

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
            //添加一个通道关闭监听
            channelFuture.channel().closeFuture().sync();


        } finally {
            eventLoopGroup.shutdownGracefully();
        }



    }
}
