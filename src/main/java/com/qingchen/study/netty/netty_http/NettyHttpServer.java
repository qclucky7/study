package com.qingchen.study.netty.netty_http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyHttpServer
 * @description:
 * @author: WangChen
 * @create: 2020-03-04 11:03
 **/
public class NettyHttpServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyHttpServerInitializer())
                    .bind(8080).sync();

            //可以加监听器
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("监听端口6666");
                }
            });
            //两种实现方式
            channelFuture.addListener(new NettyFutureListener());

            channelFuture.channel().closeFuture().sync();


        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
