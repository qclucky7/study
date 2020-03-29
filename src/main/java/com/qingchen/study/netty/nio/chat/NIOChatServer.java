package com.qingchen.study.netty.nio.chat;

import com.qingchen.study.netty.nio.server.NIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOChat
 * @description:
 * @author: WangChen
 * @create: 2020-03-01 17:43
 **/
public class NIOChatServer {

    private static Logger logger = LoggerFactory.getLogger(NIOServer.class);

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static final int PORT = 8080;

    public NIOChatServer() {

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            logger.error("服务器初始化失败...",e);
        }
    }


    public void listen() throws Exception {

        while (true){
            //没有事件
            if (selector.select(2000) == 0){
                logger.info("没有client连接...等待中");
                continue;

            }
            //拿到selector中得key集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //拿到迭代器
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){

                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isValid() && selectionKey.isAcceptable()){
                    handleAccept(selectionKey);
                }
                if (selectionKey.isValid() && selectionKey.isReadable()){
                    handleGetData(selectionKey);

                }
                if (selectionKey.isValid() && selectionKey.isWritable()){

                }
                if (selectionKey.isValid() && selectionKey.isConnectable()){

                }

                //删除当前得key,避免重复操作
                iterator.remove();


            }

        }



    }

    /**
     * 处理accept事件
     * @param selectionKey
     * @throws IOException
     */
    private void handleAccept(SelectionKey selectionKey){

        try{
            //得到当前client通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            //设置成非阻塞
            socketChannel.configureBlocking(false);
            socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(2048));
            logger.info("客户端" + socketChannel.getLocalAddress().toString() + "上线了");

        } catch (IOException ex){
            logger.error("accept error",ex);
        }

    }

    private void handleGetData(SelectionKey selectionKey){

        //得到当前client得通道
        SocketChannel channel = (SocketChannel)selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        //设置成非阻塞和设置注册到selector关联selectionKey的事件
        try {
            if ((channel.read(byteBuffer)) != -1){
                String message = new String(byteBuffer.array(), StandardCharsets.UTF_8);
                logger.info("服务器接收到消息:" + message);
                handleForwardMessage(message, channel);
            }
        } catch (IOException e) {
            try {
                logger.warn(channel.getLocalAddress() + "下线了");
                selectionKey.channel();
                channel.close();
            } catch (IOException ex) {
                logger.error("通道关闭失败...");
            }
        }

    }

    /**
     * 转发消息
     * @param message
     * @param socketChannel
     */
    private void handleForwardMessage(String message, SocketChannel socketChannel){

        for (SelectionKey selectionKey : selector.keys()){
            Channel channel = selectionKey.channel();
            if (channel instanceof SocketChannel && channel != socketChannel){
                try {
                    ((SocketChannel) channel).write(ByteBuffer.wrap(message.getBytes()));
                } catch (IOException e) {
                    logger.warn("读取数据失败...");
                }
            }

        }


    }

    public static void main(String[] args) throws Exception {

        NIOChatServer nioChatServer = new NIOChatServer();
        nioChatServer.listen();
    }
}
