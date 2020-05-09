package com.qingchen.study.lock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Deadlock
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 11:24
 **/
public class Deadlock implements Runnable{

     private final String lockA;
     private final String lockB;

    public Deadlock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "持有锁"+ lockA + "想要获取锁" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "持有锁"+ lockB + "想要获取锁" + lockA);
            }
        }
    }
}
