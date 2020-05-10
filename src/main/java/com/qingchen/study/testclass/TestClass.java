package com.qingchen.study.testclass;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TestClass
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 11:09
 **/
public class TestClass {

    /**
     * 修饰静态方法
     */
    public synchronized static void test1() {
        System.out.println("test1");
    }

    /**
     * 修饰实例方法
     */
    public synchronized void test2(){
        System.out.println("test2");
}

    /**
     * 修饰代码块
     */
    public void test3(){
        synchronized (this){
            System.out.println("test3");
        }
    }


}
