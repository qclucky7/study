package com.qingchen.study.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PhantomReferenceTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 15:57
 **/
public class PhantomReferenceTest {

    @Test
    public void myTest() throws Exception{

        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> objectPhantomReference = new PhantomReference<>(new Object(), objectReferenceQueue);

        System.out.println(objectPhantomReference.get());
        System.out.println(objectReferenceQueue.poll());

        System.gc();
        TimeUnit.SECONDS.sleep(1);

        System.out.println(objectReferenceQueue.poll());
    }
}
