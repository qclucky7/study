package com.qingchen.study.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @ClassName CglibProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-10-19 14:16
 **/
public class CglibProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getProxyInstance(Class<T> clazz, MethodInterceptor methodInterceptor){
        //创建一个工具类
        Enhancer enhancer = new Enhancer();
        //设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        //设置父类
        enhancer.setSuperclass(clazz);
        //设置回调
        enhancer.setCallback(methodInterceptor);
        //创建子类对象,即代理对象
        return (T) enhancer.create();
    }
}
