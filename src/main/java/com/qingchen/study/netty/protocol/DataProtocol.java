package com.qingchen.study.netty.protocol;

/**
 * @ClassName DataProtocol
 * @description: 可以定义协议包. 避免粘包拆包问题
 * @author: WangChen
 * @create: 2020-03-06 17:53
 **/
public class DataProtocol {

    private int length;

    private byte[] bytes;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
