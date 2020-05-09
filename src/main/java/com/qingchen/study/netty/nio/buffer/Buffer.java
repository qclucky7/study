package com.qingchen.study.netty.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @ClassName Buffer
 * @description:
 * @author: WangChen
 * @create: 2020-02-29 19:07
 **/
public class Buffer {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(5);
        //分配在系统内存中
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        //分配至jvm内存中
        ByteBuffer allocate = ByteBuffer.allocate(10);


        for (int i = 0; i < intBuffer.capacity() ; i++) {


            intBuffer.put(i);
        }


        /**
         *    一个标记
         *     private int mark = -1;
         *     下一个都或者写得索引位置
         *     private int position = 0;
         *     缓冲区得终点  不能超过这个极限值
         *     private int limit;
         *     private int capacity;
         */
        /**
         * public final Buffer flip() {
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         *     }
         */
        //放buffer里面存或者读取都得用这个转换
        intBuffer.flip();
        //设置从哪个索引开始都读取
        intBuffer.position(1);
        //设置只能读取都哪个位置
        intBuffer.limit(3);

        //重置几个参数,数据还在,下次覆盖这些数据
        intBuffer.clear();
        //当前位置合limit之前是否还有元素
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
