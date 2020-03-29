package com.qingchen.study.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName CunstomDecode
 * @description:
 * @author: WangChen
 * @create: 2020-03-06 17:56
 **/
public class CustomEncode extends MessageToByteEncoder<DataProtocol> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, DataProtocol dataProtocol, ByteBuf byteBuf) throws Exception {

        byteBuf.writeBytes(dataProtocol.getBytes());
        byteBuf.writeInt(dataProtocol.getLength());
    }
}
