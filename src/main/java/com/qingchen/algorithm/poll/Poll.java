package com.qingchen.algorithm.poll;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Poll
 * @description:
 * @author: WangChen
 * @create: 2020-06-14 18:25
 **/
public class Poll {

    private static String[] proxy = {"ip:001", "ip:002", "ip:003", "ip:004", "ip:005"};

    private int counter = proxy.length - 1;
    private int total = proxy.length;

    private static final Object LOCK = new Object();

    public String getProxy() throws InterruptedException {
        synchronized (LOCK) {
            if (counter == proxy.length) {
                counter = 0;
            }
            String ip = proxy[counter];
            counter++;
            TimeUnit.MILLISECONDS.sleep(1);
            return ip;
        }
    }

    public String getProxy1(){
        synchronized (LOCK) {
            counter = (counter + 1) % total;
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return proxy[counter];
        }
    }



}
