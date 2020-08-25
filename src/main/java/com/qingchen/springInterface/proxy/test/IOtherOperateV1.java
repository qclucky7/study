package com.qingchen.springInterface.proxy.test;

/**
 * @ClassName IOtherOperateV1
 * @description:
 * @author: WangChen
 * @create: 2020-07-29 16:45
 **/
public class IOtherOperateV1 implements IOtherOperate {
    @Override
    public void say() {
        System.out.println("IOtherOperateV1执行...");
    }
}
