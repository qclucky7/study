package com.qingchen.study.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 12:08
 **/
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * ClassLoader loader :指定目标对象得类加载器,固定方法
     * Class<?>[] interfaces, 目标接口得实现类型, 使用泛型方法确认类型
     * invocationHandler h
     * @return
     */
    public Object getProxyInstance(){

        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("代理！！！！");
                    return method.invoke(target, args);
                }
        );
    }

    public static void main(String[] args) {
        // 让代理对象的class文件写入到磁盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyFactory proxyFactory = new ProxyFactory(new IProxyImp());
        IProxy proxyInstance = (IProxy) proxyFactory.getProxyInstance();
        proxyInstance.doSomething();
    }
}
