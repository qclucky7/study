package com.qingchen.study.netty.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @ClassName NettyServer
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 17:35
 **/
public class NettyServer {

    public static void startServer(String hostName){
        startServer0(hostName, 8080);
    }

    public static void startServer(String hostName, int port){
        startServer0(hostName, port);
    }

    private static void startServer0(String hostName, int port){

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                           .channel(NioServerSocketChannel.class)
                           .childHandler(new ChannelInitializer<SocketChannel>() {
                               @Override
                               protected void initChannel(SocketChannel socketChannel) throws Exception {
                                   ChannelPipeline pipeline = socketChannel.pipeline();
                                   pipeline.addLast(new StringDecoder());
                                   pipeline.addLast(new StringEncoder());
                                   pipeline.addLast(new NettyServerHandler());

                               }
                           });
            ChannelFuture channelFuture = serverBootstrap.bind(hostName, port).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }



    }
}
