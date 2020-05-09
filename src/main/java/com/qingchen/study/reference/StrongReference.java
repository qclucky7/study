package com.qingchen.study.reference;

import org.junit.Test;

import java.lang.ref.SoftReference;

/**
 * @ClassName StrongReference
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 14:52
 **/
public class StrongReference {


    @Test
    public void myTest(){
        Object o1 = new Object();
        //o1指针指向堆内存的object对象
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);
        System.out.println(o1);
    }
}
