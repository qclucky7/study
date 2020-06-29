package com.qingchen.algorithm.poll;

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

        ExecutorService pool = Executors.newFixedThreadPool(5);

        Poll poll = new Poll();

        for (int i = 0; i < 20 ; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getId() + "----" + poll.getProxy1() ));
        }
    }
}
