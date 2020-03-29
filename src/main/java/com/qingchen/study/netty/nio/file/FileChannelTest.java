package com.qingchen.study.netty.nio.file;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName FileChannel
 * @description:
 * @author: WangChen
 * @create: 2020-02-29 20:34
 **/
public class FileChannelTest {

    public static void main(String[] args) throws Exception {

        String data = "hello,channel,12345";

        ByteBuffer allocate = ByteBuffer.allocate(5);


        allocate.put(data.getBytes());


        //这个方法有多少字节读取多少字节
        ByteBuffer wrap = ByteBuffer.wrap(data.getBytes());

        //allocate.flip();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\1.text");
        FileChannel channel = fileOutputStream.getChannel();
        channel.write(wrap);
        fileOutputStream.close();
    }
}
