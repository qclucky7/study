package com.qingchen.study.base64;

import org.junit.Test;

import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Base64Test
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 16:20
 **/
public class Base64Test {

    @Test
    public synchronized void myTest(){

        AtomicInteger atomicInteger = new AtomicInteger();


        String s = Base64.getEncoder().encodeToString("我来了".getBytes());
        System.out.println(s);
        String s1 = new String(Base64.getDecoder().decode(s.getBytes()));
        System.out.println(s1);


    }
}
