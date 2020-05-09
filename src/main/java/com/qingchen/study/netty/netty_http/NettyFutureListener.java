package com.qingchen.study.netty.netty_http;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @ClassName NettyFutureListener
 * @description:
 * @author: WangChen
 * @create: 2020-03-04 14:46
 **/
public class NettyFutureListener implements ChannelFutureListener {


    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {

        channelFuture.isSuccess();
        channelFuture.isDone();
        channelFuture.isVoid();
        channelFuture.isCancelled();
        channelFuture.isCancellable();
    }
}
