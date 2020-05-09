package com.qingchen.study.netty.netty_chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @ClassName NettyClient
 * @description:
 * @author: WangChen
 * @create: 2020-03-04 18:46
 **/
public class NettyClient {

    private final String host;
    private final int port;


    public NettyClient (String host, int port){
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
                channel.writeAndFlush(s);
            }

            channelFuture.channel().closeFuture().sync();


        } finally {
            eventExecutors.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        //new NettyClient("localhost", 8080).start();


        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        List<Integer> newList = new ArrayList<>(integers);

        newList.remove(0);

        System.out.println("复制到新的list = " + integers.toString());

        List<Integer> c = integers;

        c.remove(0);

        System.out.println("引用的list = " + integers.toString());


    }
}
