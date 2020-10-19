package com.qingchen.study.threadLocal;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocalTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-08 18:45
 **/
public class ThreadLocalTest {

    //private static final ThreadLocal threadLocal = ThreadLocal.withInitial(null);


    private static final ThreadLocal<String> inheritableThreadLocal = InheritableThreadLocal.withInitial(()->"1111");

    //懒加载  第一次赋值的时候才会去创建map
//    public void set(T value) {
//        Thread t = Thread.currentThread();
//        ThreadLocal.ThreadLocalMap map = getMap(t);
//        if (map != null)
//            map.set(this, value);
//        else
//            createMap(t, value);
//    }

      //初始值为16的Entry数组
//    ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
//        table = new ThreadLocal.ThreadLocalMap.Entry[INITIAL_CAPACITY];
//        int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
//        table[i] = new ThreadLocal.ThreadLocalMap.Entry(firstKey, firstValue);
//        size = 1;
//        setThreshold(INITIAL_CAPACITY);
//    }

    @Test
    public void myTest(){

        new Object();
        new HashMap<>();
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main = " + inheritableThreadLocal.get());

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("child = " + inheritableThreadLocal.get());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        objectCompletableFuture.join();
    }
}
