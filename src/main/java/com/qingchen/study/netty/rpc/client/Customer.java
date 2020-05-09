package com.qingchen.study.netty.rpc.client;

import com.qingchen.study.netty.rpc.commonI.ServiceTest;

/**
 * @ClassName Customer
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 17:27
 **/
public class Customer {

    public static final String protocolName = "ServiceTest#sendMessage#";

    public static void main(String[] args) {

        ServiceTest proxyInstance = (ServiceTest) new NettyClient().getProxyInstance(ServiceTest.class, protocolName);

        String message = proxyInstance.sendMessage("消息发出");

        System.out.println("收到服务端消息 = " +message);
    }
}
