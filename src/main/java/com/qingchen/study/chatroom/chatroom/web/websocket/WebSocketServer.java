package com.qingchen.study.chatroom.chatroom.web.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 描述: Netty WebSocket服务器
 *      使用独立的线程启动
 * @author Kanarien 
 * @version 1.0
 * @date 2018年5月18日 上午11:22:51
 */
@Component("nettyWebSocketServer")
public class WebSocketServer implements InitializingBean, DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	private EventLoopGroup bossGroup;
	private EventLoopGroup workGroup;
	private ChannelFuture channelFuture;

	@Autowired
	private WebSocketChildChannelHandler webSocketChildChannelHandler;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("开始启动了.....????");
		new Thread(() -> startServer0(8082)).start();
		System.out.println("结束了.....????");
	}


	private void startServer0(int port){

		bossGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup();

		try {
			long start = System.currentTimeMillis();
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			//boss辅助客户端的tcp连接请求  worker负责与客户端之前的读写操作
			serverBootstrap.group(bossGroup, workGroup)
					//配置客户端的channel类型
					.channel(NioServerSocketChannel.class)
					//配置TCP参数，握手字符串长度设置
					.option(ChannelOption.SO_BACKLOG, 1024)
					//TCP_NODELAY算法，尽可能发送大块数据，减少充斥的小块数据
					.option(ChannelOption.TCP_NODELAY, true)
					//开启心跳包活机制，就是客户端、服务端建立连接处于ESTABLISHED状态，超过2小时没有交流，机制会被启动
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					//配置固定长度接收缓存区分配器
					.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(592048))
					//绑定I/O事件的处理类,WebSocketChildChannelHandler中定义
					.childHandler(webSocketChildChannelHandler);

			channelFuture = serverBootstrap.bind(port).sync();
			logger.info("Netty Websocket服务器启动完成，耗时 " + (System.currentTimeMillis() - start) + " ms，已绑定端口 " + port + " 阻塞式等候客户端连接");
			channelFuture.channel().closeFuture().sync();

		} catch (Exception e){
			logger.error("Netty Websocket服务器启动失败 {}", ExceptionUtils.getStackTrace(e));
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();

		}



	}


	/**
	 * 描述：关闭Netty Websocket服务器，主要是释放连接
	 *     连接包括：服务器连接serverChannel，
	 *     客户端TCP处理连接bossGroup，
	 *     客户端I/O操作连接workerGroup
	 *
	 *     若只使用
	 *         bossGroupFuture = bossGroup.shutdownGracefully();
	 *         workerGroupFuture = workerGroup.shutdownGracefully();
	 *     会造成内存泄漏。
	 */
	@Override
	public void destroy(){
		System.out.println("容器销毁执行................");
		channelFuture.channel().close();
		Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
		Future<?> workerGroupFuture = workGroup.shutdownGracefully();

		try {
			bossGroupFuture.await();
			workerGroupFuture.await();
		} catch (InterruptedException ignore) {
			ignore.printStackTrace();
		}
	}



}
