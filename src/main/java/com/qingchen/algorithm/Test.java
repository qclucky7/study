package com.qingchen.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Test
 * @description:
 * @author: WangChen
 * @create: 2020-06-14 19:30
 **/
public class Test {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10 ; i++) {
            pool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getId() + "----" + Poll.getProxy() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
