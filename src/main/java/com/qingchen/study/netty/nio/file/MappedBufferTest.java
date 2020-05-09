package com.qingchen.study.netty.nio.file;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MappedBufferTest
 * @description: 可以让文件在内存中修改(堆外内存),操作系统不需要拷贝一次
 * @author: WangChen
 * @create: 2020-03-01 12:14
 **/
public class MappedBufferTest {

    public static void main(String[] args) throws Exception {

        /**
         * r
         * 以只读的方式打开文本，也就意味着不能用write来操作文件
         * rw
         * 读操作和写操作都是允许的
         * rws
         * 每当进行写操作，同步的刷新到磁盘，刷新内容和元数据
         * rwd
         * 每当进行写操作，同步的刷新到磁盘，刷新内容
         */
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\Huawei Share\\OneHop\\test.mp4", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 参数1: 模式 读写,只读,只写
         * 参数2: 从什么位置开始读取
         * 参数3: 最多修改几个字节
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, randomAccessFile.length());
    }

}
