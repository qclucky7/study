package com.qingchen.study.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName ProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 15:27
 **/
public class ProxyFactory implements MethodInterceptor {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){

        //创建一个工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建子类对象,即代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理,不需要实现接口,直接调用！");
        return method.invoke(target, objects);
    }

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new Target());
        Target proxyInstance = (Target) proxyFactory.getProxyInstance();
        proxyInstance.doSomething();
    }
}
