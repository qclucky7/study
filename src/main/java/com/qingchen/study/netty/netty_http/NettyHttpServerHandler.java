package com.qingchen.study.netty.netty_http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @ClassName NettyHttpServerHandler
 * @description: SimpleChannelInboundHandler是ChannelInboundHandlerAdapter得子类 HttpObject是客户端通信封装得对象
 * @author: WangChen
 * @create: 2020-03-04 11:17
 **/
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {


        if (httpObject instanceof HttpRequest){


            System.out.println("客户端地址:" + channelHandlerContext.channel().remoteAddress());

            //每个浏览器都有唯一的值
            int pipelineHashCode = channelHandlerContext.channel().pipeline().hashCode();
            int nettyHttpServerHandlerHashCode = this.hashCode();

            System.out.println("NettyHttpServerHandler hashCode = " + nettyHttpServerHandlerHashCode);
            System.out.println("pipeline hashCode = " + pipelineHashCode);


            ByteBuf byteBuf = Unpooled.copiedBuffer("abc", CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            HttpRequest httpRequest  = (HttpRequest) httpObject;

            URI uri = new URI(httpRequest.uri());
            if ("需要过滤的请求地址".equals(uri.getPath())){
                System.out.println("此请求已经被过滤");
                return;
            }

            channelHandlerContext.writeAndFlush(response);
            //channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);

        }

    }
}
