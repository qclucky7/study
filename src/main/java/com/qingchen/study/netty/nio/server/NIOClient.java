package com.qingchen.study.netty.nio.server;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NIOClient
 * @description:
 * @author: WangChen
 * @create: 2020-03-01 15:42
 **/
public class NIOClient {

    public static void main(String[] args) throws Exception{

        //client得到socketChannel
        SocketChannel socketChannel = SocketChannel.open();
        //设置成非阻塞
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        if (!socketChannel.connect(inetSocketAddress)){
            if (!socketChannel.finishConnect()){
                System.out.println("客户端没有完成连接....");
            }
        }

        while (true){
            TimeUnit.SECONDS.sleep(2);
            socketChannel.write(ByteBuffer.wrap("王晨已经连接".getBytes()));
            if ((socketChannel.read(byteBuffer)) != -1){
                System.out.println("收到服务器得数据了:" + new String(byteBuffer.array(), StandardCharsets.UTF_8));
                byteBuffer.clear();
                System.out.println(byteBuffer.array().length);
            }
        }



    }
}
