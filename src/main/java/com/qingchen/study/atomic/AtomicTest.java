package com.qingchen.study.atomic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName AtomicTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-13 15:53
 **/
public class AtomicTest {

    private AtomicInteger atomicInteger = new AtomicInteger();
    private AtomicLong atomicLong = new AtomicLong();
    private LongAdder longAdder = new LongAdder();

    @Test
    public void myTest(){

        //这个返回值是旧值
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);
        //返回值是最新得值
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
    }
}
