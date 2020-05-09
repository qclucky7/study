package com.qingchen.study.netty.nio.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * @ClassName FileChannel2Test
 * @description:
 * @author: WangChen
 * @create: 2020-03-01 10:34
 **/
public class FileChannel2Test {

    public static void main(String[] args) throws Exception {


        ByteBuffer read = ByteBuffer.allocate(1024);

        FileInputStream fileInputStream = new FileInputStream("D:\\Huawei Share\\OneHop\\test.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\2.mp4");
        FileChannel readChannel = fileInputStream.getChannel();
        FileChannel writeChannel = fileOutputStream.getChannel();
        //第一种
        int line;
        while ((line = readChannel.read(read))!= -1){
            read.flip();
            writeChannel.write(read);
            read.clear();
        }

        //第二种
        writeChannel.transferFrom(readChannel, 0, readChannel.size());

        fileInputStream.close();
        fileOutputStream.close();

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //将buffer转换成一个只读得buffer
        ByteBuffer byteBuffer1 = byteBuffer.asReadOnlyBuffer();

    }
}
