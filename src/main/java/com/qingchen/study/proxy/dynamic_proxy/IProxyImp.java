package com.qingchen.study.proxy.dynamic_proxy;

/**
 * @ClassName IProxyImp
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 11:50
 **/
public class IProxyImp implements IProxy {
    @Override
    public void doSomething() {
        System.out.println("实际做的事情！");
    }
}
