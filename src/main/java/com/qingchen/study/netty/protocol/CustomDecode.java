package com.qingchen.study.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @ClassName CunstomDecode
 * @description: 解码器
 * @author: WangChen
 * @create: 2020-03-06 17:56
 **/
public class CustomDecode  extends ReplayingDecoder<DataProtocol> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        DataProtocol dataProtocol = new DataProtocol();
        dataProtocol.setLength(length);
        dataProtocol.setBytes(bytes);

        list.add(dataProtocol);


    }
}
