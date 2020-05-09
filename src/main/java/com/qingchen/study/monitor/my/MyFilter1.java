package com.qingchen.study.monitor.my;

/**
 * @ClassName MyFilter1
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 14:56
 **/
public class MyFilter1 extends AbstractLinkedProcessorSlot {

    @Override
    public void carryOn(long number) {

        System.out.println("filter1执行");
        System.out.println(number);

        fireEntity(number);
    }

}
