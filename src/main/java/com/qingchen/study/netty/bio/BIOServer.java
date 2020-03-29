package com.qingchen.study.netty.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName BIOServer
 * @description:
 * @author: WangChen
 * @create: 2020-02-29 18:03
 **/
public class BIOServer {

    public static void main(String[] args)  throws  Exception{

        //如果有客户端连接，就用一个线程与之通信
        ExecutorService pool = Executors.newCachedThreadPool();


        //创建一个serverSocket
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动了");
        while (true){
            //监听  等待客户端连接  这个监听是main线程
            final Socket socket = serverSocket.accept();
            System.out.println("监听线程为"+Thread.currentThread().getName());
            //得到客户端连接
            System.out.println("客户端连接...");
            pool.execute(() -> {
                handleSocket(socket);
            });


        }
    }

    public static void handleSocket(Socket socket){

        //通过socket获取到输入流

        System.out.println("当前通讯线程"+Thread.currentThread().getName());

        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            int read;
            while ((read = inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes,0,read));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
