package com.qingchen.study.netty.nio.server;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOServer
 * @description:
 * @author: WangChen
 * @create: 2020-03-01 15:18
 **/
public class NIOServer {

    private static Logger logger = LoggerFactory.getLogger(NIOServer.class);

    public static void main(String[] args) throws Exception{

        //开启服务器
        ServerSocketChannel serverSocketChannel =  ServerSocketChannel.open();
        //得到一个selector对象
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel注册到selector  关心事件为accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //所有注册都selector得数量
        selector.keys();
        //当前有事件发生得key得数量
        selector.selectedKeys();

        while (true){
            if (selector.select(3000) == 0){
                System.out.println("服务器等待3秒");
                continue;
            }
            //获得当前注册在selector上得所有selectionKey用于判断是那种事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            if (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //拿到这个key是关于accept事件得
                if (key.isAcceptable()){
                    handleAccept(key);
                }
                if (key.isReadable() && key.isValid()){
                    handleMsg(key);
                }
                //移除当前key
                iterator.remove();
            }
        }

    }

    public static void handleAccept(SelectionKey key) throws Exception{

        //得到client通道
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        //必须把通道设置成非阻塞
        socketChannel.configureBlocking(false);
        //将client通道注册到selector,并且关联一个buffer
        socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));

    }

    public static void handleMsg(SelectionKey key) throws Exception{

        //如果是读事件,根据拿到得key去拿到对应得channel
        SocketChannel channel = (SocketChannel)key.channel();
        //根据key去拿到相应通道对应得ByteBuffer
        ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
        //将channel得数据读取到buffer中
        byteBuffer.clear();
        try {
            if ((channel.read(byteBuffer)) != -1){
                System.out.println("从客户端传来的数据:" + new String(byteBuffer.array(), StandardCharsets.UTF_8));
                byteBuffer.flip();
                channel.write(ByteBuffer.wrap("服务器已经接受数据".getBytes()));
                //为下一次读取或写入做准备
                key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            } else {
                channel.close();
            }
        } catch (IOException e) {
            key.cancel();
            channel.socket().close();
            channel.close();
            logger.warn("connect {}",e.getMessage());
        }

    }
}
