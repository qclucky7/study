package com.qingchen.algorithm;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName PollWeighted
 * @description:
 * @author: WangChen
 * @create: 2020-06-14 20:47
 **/
public class PollWeighted {

    private static String[] proxy = {"ip:001", "ip:002", "ip:003", "ip:004", "ip:005"};

    private static int counter = 0;

    private static final Object LOCK = new Object();

    public static String getProxy() throws InterruptedException {
        synchronized (LOCK){
            if (counter == proxy.length){
                counter = 0;
            }
            String ip = proxy[counter];
            counter ++;
            TimeUnit.MILLISECONDS.sleep(1);
            return ip;
        }
    }
}
