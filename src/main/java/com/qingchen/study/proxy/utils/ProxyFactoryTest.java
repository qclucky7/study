package com.qingchen.study.proxy.utils;

import org.junit.Test;

/**
 * @ClassName ProxyFactoryTest
 * @description:
 * @author: WangChen
 * @create: 2020-08-19 15:33
 **/
public class ProxyFactoryTest {


    @Test
    public void test(){

        ProxyTest proxy = (ProxyTest)ProxyFactory.createProxy(new ProxyImpl());

        proxy.say();
    }


}
