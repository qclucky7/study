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

    public synchronized Object test(){
        synchronized (this){

        }
        return null;
    }


}
