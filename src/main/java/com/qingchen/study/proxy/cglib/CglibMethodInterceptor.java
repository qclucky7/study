package com.qingchen.study.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName ProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 15:27
 **/
public class CglibMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibMethodInterceptor method = " + method.getName());
        System.out.println("CglibMethodInterceptor methodProxy = " + methodProxy.getSuperName());
        //System.out.println("CglibMethodInterceptor Object = " + o.toString());
        System.out.println("CglibMethodInterceptor args = " + Arrays.toString(args));
        System.out.println("cglib代理,不需要实现接口,直接调用！");
        return methodProxy.invokeSuper(o, args);
    }

}
