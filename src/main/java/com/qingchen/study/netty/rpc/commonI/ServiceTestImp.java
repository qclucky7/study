package com.qingchen.study.netty.rpc.commonI;

import com.qingchen.study.utils.StringUtils;

/**
 * @ClassName ServiceTestImp
 * @description:
 * @author: WangChen
 * @create: 2020-03-07 17:31
 **/
public class ServiceTestImp implements ServiceTest {

    @Override
    public String sendMessage(String msg) {

        System.out.println("服务端收到消息！");

        if (StringUtils.isEmpty(msg)){
            return "服务端收到消息了,消息为空";
        } else {
            return "服务端收到消息:" + msg;
        }
    }
}
