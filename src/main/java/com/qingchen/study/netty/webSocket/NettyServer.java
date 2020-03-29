package com.qingchen.study.netty.webSocket;

import com.qingchen.study.netty.heartbeat.CustomServerHandler;
import com.qingchen.study.netty.netty_http.NettyFutureListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServer
 * @description:
 * @author: WangChen
 * @create: 2020-03-05 14:34
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
                         * @param socketChannel
                         * @throws Exception
                         */
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //http编解码器
                            pipeline.addLast(new HttpServerCodec());
                            //以块的方式写,添加ChunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http请求是分段的,HttpObjectAggregator将分段的数据聚合
                            //也就是为什么浏览器发送大量数据的时候会发多次的http请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //webSocket是以帧的形式传递
                            //webSocketFrame下面有6个子类
                            //浏览器请求是:ws://localhost:8080/hello 和下面的对应
                            //WebSocketServerProtocolHandler可以将http协议升级为ws协议,保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //自定义handler处理业务逻辑
                            pipeline.addLast(new ServerWebSocketHandler());
                        }
                    })
                    .bind(8080).sync();

            //可以加监听器
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("监听端口8080");
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
