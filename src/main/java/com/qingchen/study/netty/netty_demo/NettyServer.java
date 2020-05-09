package com.qingchen.study.netty.netty_demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName NettyServer
 * @description:
 * @author: WangChen
 * @create: 2020-03-02 20:21
 **/
public class NettyServer {


    public static void main(String[] args) throws Exception{

        //创建bossGroup和workGroup
        //bossGroup只负责连接请求, 具体业务处理交给workGroup 两个线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            //服务器相关配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置两个线程组
            bootstrap.group(bossLoopGroup, workLoopGroup)
                    //使用NioServerSocketChannel作为服务器通道得实现
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到得连接数  给channel添加配置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活跃得连接个数 给接收的通道添加配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //handler和childHandler区别是 handler是加在bossGroup上面,另一个是加在workGroup上
                    .handler(null)
                    //给pipeline设置处理器  业务处理类
                    .childHandler(new HttpServerInitializer());

            System.out.println("服务器启动...");
            //绑定一个端口并且同步处理
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();

            //可以加监听器
            channelFuture.addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("监听端口6666");
                }
            });

            //对通道得关闭时间进行监听
            channelFuture.channel().closeFuture().sync();


        } finally {
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }




    }

}
