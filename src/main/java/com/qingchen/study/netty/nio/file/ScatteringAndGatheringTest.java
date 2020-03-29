package com.qingchen.study.netty.nio.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @ClassName ScatteringAndGatheringTest
 * Scattering : 将数据写入buffer时,可以采取buffer[],依次写入(分散)
 * Gathering : 从buffer中读取数据,采用buffer[],依次读取
 * @author: WangChen
 * @create: 2020-03-01 12:27
 **/
public class ScatteringAndGatheringTest {

    //使用
    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);
        serverSocketChannel.socket().bind(inetSocketAddress);

        System.out.println("服务器启动了...");


        /** 等待客户端连接 */
        SocketChannel socketChannel = serverSocketChannel.accept();

        System.out.println("客户端有连接了...");

        ByteBuffer[] byteBuffers = new ByteBuffer[1];
        byteBuffers[0] = ByteBuffer.allocate(5);
        //byteBuffers[1] = ByteBuffer.allocate(5);

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\2.text");
        FileChannel writeChannel = fileOutputStream.getChannel();

        while ((socketChannel.read(byteBuffers)) != -1){
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                System.out.println("position = " + byteBuffer.position() + "limit = " + byteBuffer.limit());
                String s = new String(byteBuffer.array(), StandardCharsets.UTF_8);
                //CharBuffer decode = StandardCharsets.UTF_8.decode(byteBuffer);
                //System.out.println("服务器收到了:"+ s);
                System.out.println("服务器收到了:"+ s);
                byteBuffer.flip();
                try {
                    writeChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    byteBuffer.clear();
                }
            });
        }


        //serverSocketChannel.close();



    }
}
