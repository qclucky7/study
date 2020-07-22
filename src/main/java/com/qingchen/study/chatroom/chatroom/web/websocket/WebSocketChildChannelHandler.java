package com.qingchen.study.chatroom.chatroom.web.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChildChannelHandler extends ChannelInitializer<SocketChannel>{


	@Autowired
	private WebSocketServerHandler webSocketServerHandler;

	@Autowired
	private HttpRequestHandler httpRequestHandler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// HTTP编码解码器
		ch.pipeline().addLast("http-codec", new HttpServerCodec());
		// 把HTTP头、HTTP体拼成完整的HTTP请求
		ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
		// 方便大文件传输，不过实质上都是短的文本数据
		ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
		ch.pipeline().addLast("http-handler", httpRequestHandler);
		ch.pipeline().addLast("websocket-handler", webSocketServerHandler);
	}

}
