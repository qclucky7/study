package com.qingchen.study.reference;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName WeakReferenceTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 15:51
 **/
public class WeakReferenceTest {

    private static final Object object = new Object();
    @Test
    public void myTest(){
        WeakReference<Object> objectWeakReference = new WeakReference<>(object);
        System.out.println(objectWeakReference.get());
        System.gc();
        System.out.println(objectWeakReference.get());
    }

    @Test
    public void myTest1(){
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer integer = new Integer(1);
        String s = "weakHashMap";
        weakHashMap.put(integer, s);
        weakHashMap.put(new Integer(1), s);
        weakHashMap.put(2, "weakHashMap2");
        System.out.println(weakHashMap.toString());

        //不手动置成null不会回收。 或者直接在put里面new可以回收。
        integer = null;

        System.gc();
        System.out.println(weakHashMap.toString());
    }
}
