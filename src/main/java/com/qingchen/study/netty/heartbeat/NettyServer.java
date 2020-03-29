package com.qingchen.study.netty.heartbeat;

import com.qingchen.study.netty.netty_http.NettyFutureListener;
import com.qingchen.study.netty.netty_http.NettyHttpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServer
 * @description:
 * @author: WangChen
 * @create: 2020-03-05 14:01
 **/
public class NettyServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {


                        /**
                         * IdleStateHandler参数
                         * long readerIdleTime, 多长时间没有读操作了, 发送一个心跳包
                         * long writerIdleTime, 多长时间没有写操作了, 发送一个心跳包
                         * long allIdleTime, 多长时间没有读写操作了, 发送一个心跳包
                         * TimeUnit unit
                         *
                         * 当IdleStateHandler触发后就会调用下一个handler的userEventTiggered的时候,该方法处理IdleStateHandler
                         * @param socketChannel
                         * @throws Exception
                         */
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            pipeline.addLast(new CustomServerHandler());
                        }
                    })
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
