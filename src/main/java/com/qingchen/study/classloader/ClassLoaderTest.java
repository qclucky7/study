package com.qingchen.study.classloader;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @ClassName ClassLoaderTest
 * @description:
 * @author: WangChen
 * @create: 2020-08-20 14:02
 **/
public class ClassLoaderTest {


    @Test
    public void test(){

        ClassLoader classLoader = ServiceLoader.class.getClassLoader();
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();

        System.out.println(classLoader);
        System.out.println(classLoader1);

        String s = "123";

        s.intern();

    }





}
