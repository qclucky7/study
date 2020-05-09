package com.qingchen.study.reference;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName SoftReferenceTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 15:01
 **/
public class SoftReferenceTest {


    @Test
    public void myTest(){
        //场景  比如读取大量图片得数据放进缓存
        SoftReference<Object> objectSoftReference = new SoftReference<>(new Object());
        System.out.println(objectSoftReference.get());
        try{
            byte[] byte1 = new byte[1024 * 1024 * 30];
        } finally {
            System.out.println("内存不够发生GC " + objectSoftReference.get());
        }

    }

}
