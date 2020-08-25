package com.qingchen.study.proxy.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @ClassName ProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-08-19 15:26
 **/
public class ProxyFactory<T> implements InvocationHandler{

    private final T target;

    private ProxyFactory(T target) {
        this.target = target;
    }

    public static Object createProxy(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new ProxyFactory<>(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        System.out.println(Arrays.toString(proxy.getClass().getInterfaces()));
        System.out.println("2222222222");
        return method.invoke(target, args);
    }
}
