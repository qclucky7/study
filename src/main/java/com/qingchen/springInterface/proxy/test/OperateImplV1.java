package com.qingchen.springInterface.proxy.test;

import org.springframework.stereotype.Component;

/**
 * @ClassName OperateImplV1
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 11:03
 **/
@Component
public class OperateImplV1 implements IOperate{

    @Override
    public void operate() {
        System.out.println("v1版本执行!");
    }
}
