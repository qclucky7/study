package com.qingchen.study.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Number
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 18:12
 **/
public class Number implements AutoCloseable{

    private int number;

    ReentrantLock lock =   new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add(){
       lock.lock();
       try{
           while (number != 0){
               try {
                   condition.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        number++;
        System.out.println(Thread.currentThread().getName() + number);
        condition.signal();

       } finally {
           lock.unlock();
       }
    }

    public void lower(){
        lock.lock();
        try{
            while (number == 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            System.out.println(Thread.currentThread().getName() + number);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
