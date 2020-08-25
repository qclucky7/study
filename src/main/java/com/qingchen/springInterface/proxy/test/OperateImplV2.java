package com.qingchen.springInterface.proxy.test;

import org.springframework.stereotype.Component;

/**
 * @ClassName OperateImplV2
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 11:03
 **/
@Component
public class OperateImplV2 implements IOperate{

    @Override
    public void operate() {
        System.out.println("v2版本执行!");
    }
}
