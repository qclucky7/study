package com.qingchen.study.netty.netty_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServerHandler
 * @description: 自定义个一个handler要继承ChannelInboundHandlerAdapter
 * @author: WangChen
 * @create: 2020-03-02 20:53
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 读取数据(客户端传来得数据)
     * @param ctx ChannelHandlerContext上下文对象, 其中包含pipeline, channel
     * @param msg 客户端发送得数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //System.out.println("ctx = " + ctx);

        //ByteBuf是netty提供得 对nio得在封装, 效率更快
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端传来得消息" + byteBuf.toString(CharsetUtil.UTF_8));

        //如果是一个耗时较长得任务  将任务交给taskQueue里面(自定义任务)
        /*ctx.channel().eventLoop().execute(() ->{
            try {
                TimeUnit.SECONDS.sleep(10);
                ctx.writeAndFlush(Unpooled.copiedBuffer("耗时长时间任务返回了",CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        //自定义定时任务
        ctx.channel().eventLoop().scheduleWithFixedDelay(() ->{
            try {
                //TimeUnit.SECONDS.sleep(10);
                ctx.writeAndFlush(Unpooled.copiedBuffer("定时任务！",CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        },3, 2, TimeUnit.SECONDS);
    }

    /**
     * 服务器返回消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器收到消息！",CharsetUtil.UTF_8));
        System.out.println("客户端地址:"+ ctx.channel().remoteAddress());
    }

    /**
     * 捕获异常关闭
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
