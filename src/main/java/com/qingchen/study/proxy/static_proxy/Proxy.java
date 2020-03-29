package com.qingchen.study.proxy.static_proxy;

/**
 * @ClassName Proxy
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 11:51
 **/
public class Proxy implements IProxy{

    private IProxy iProxy;

    public Proxy(IProxy iProxy) {
        this.iProxy = iProxy;
    }

    @Override
    public void doSomething() {

        System.out.println("代理>....做些什么事情吧");
        iProxy.doSomething();
        System.out.println("之后做些什么事情吧");

    }

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new IProxyImp());
        proxy.doSomething();
    }
}
