package com.qingchen.study.monitor.my;

/**
 * @ClassName MyFilter2
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 14:57
 **/
public class MyFilter2 extends AbstractLinkedProcessorSlot {

    @Override
    public void carryOn(long number) {

        System.out.println("myFilter2执行");

        this.fireEntity(number);

        System.out.println(".......");

    }
}
