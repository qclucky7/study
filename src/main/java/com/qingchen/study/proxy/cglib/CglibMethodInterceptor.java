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


    /**
     * @param o           被代理的对象（需要增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
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
