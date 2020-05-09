package com.qingchen.study.lock;

import io.swagger.models.auth.In;
import org.junit.Test;

/**
 * @ClassName Lock
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 18:12
 **/
public class Lock {

    /**
     * 多线程得判断要用while
     */
    @Test
    public void myTest(){


        try (Number number = new Number()){
            new Thread(() -> {
                for (int i = 0; i < 5 ; i++) {
                    number.add();
                }
            }, "a").start();

            new Thread(() -> {
                for (int i = 0; i < 5 ; i++) {
                    number.lower();
                }
            }, "b").start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws InterruptedException {
//        String lockA = "lockA";
//        String lockB = "lockB";
//        new Thread( new Deadlock(lockA, lockB), "a").start();
//        new Thread( new Deadlock(lockB, lockA),"b").start();
        //Thread.sleep(Integer.MAX_VALUE);
        byte[] bytes = new byte[11 * 1024 * 1024];
    }


}
