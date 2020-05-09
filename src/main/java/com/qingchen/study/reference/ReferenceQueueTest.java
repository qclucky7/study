package com.qingchen.study.reference;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReferenceQueueTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 16:23
 **/
public class ReferenceQueueTest {


    @Test
    public void myTest() throws Exception{
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        WeakReference<Object> objectWeakReference = new WeakReference<>(new Object(), objectReferenceQueue);

        System.out.println(objectWeakReference.get());
        System.out.println(objectReferenceQueue.poll());

        System.gc();

        TimeUnit.SECONDS.sleep(1);


        System.out.println(objectWeakReference.get());
        System.out.println(objectReferenceQueue.poll());
    }

}
