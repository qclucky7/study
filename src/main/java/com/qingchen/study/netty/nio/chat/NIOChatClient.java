package com.qingchen.study.netty.nio.chat;

import com.qingchen.study.netty.nio.server.NIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NIOChatClient
 * @description:
 * @author: WangChen
 * @create: 2020-03-01 18:41
 **/
public class NIOChatClient {

    private static Logger logger = LoggerFactory.getLogger(NIOServer.class);

    private final String HOST = "127.0.0.1";
    private final int PORT = 8080;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public NIOChatClient() {
        try {
            socketChannel = SocketChannel.open();
            selector = Selector.open();
            socketChannel.connect(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            userName = socketChannel.getLocalAddress().toString();
            System.out.println(userName);
        } catch (IOException e) {
            logger.error("client 初始化失败...",e);
        }
    }

    /**
     * 客户端发消息
     * @param message
     */
    public void sendMessage(String message){

        try {
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        } catch (IOException ex){
            logger.warn("客户端写入数据失败",ex);
        }
    }

    /**
     * 读取服务器消息
     */
    public void readMessage(){

        try {
            if (selector.select(2000) > 0){
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> iterator = keys.iterator();

                while (iterator.hasNext()){

                    SelectionKey key = iterator.next();
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    logger.info(userName + "收到消息:" + new String(byteBuffer.array(), StandardCharsets.UTF_8));

                    iterator.remove();
                }


            }
        } catch (IOException ex){
           logger.warn("client 读取数据失败...",ex);
        }
    }

    public static void main(String[] args) {

        NIOChatClient nioChatClient = new NIOChatClient();

        ExecutorService pool = Executors.newFixedThreadPool(1);

        pool.execute(() ->{
            while (true){
                try {
                    nioChatClient.readMessage();
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception ex){

                }
            }
        });


        Scanner scanner = new Scanner(System.in);
        while (true){
            if (scanner.hasNextLine()){
                nioChatClient.sendMessage(scanner.nextLine());
            }

        }
    }

}
