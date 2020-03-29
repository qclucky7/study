package com.qingchen.study.netty.netty_chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @ClassName NettyClient
 * @description:
 * @author: WangChen
 * @create: 2020-03-04 18:46
 **/
public class NettyClient1 {

    private final String host;
    private final int port;


    public NettyClient1(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{

        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientInitializer())
                    .connect(host, port).sync();

            Channel channel = channelFuture.channel();

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                System.out.println("mesggg = " +s);
                channel.writeAndFlush(s);
            }

            channelFuture.channel().closeFuture().sync();


        } finally {
            eventExecutors.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        new NettyClient1("localhost", 8080).start();
    }
}
