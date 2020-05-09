package com.qingchen.study.monitor.my;

import org.junit.Test;

/**
 * @ClassName MyTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 15:06
 **/
public class MyTest {

    @Test
    public void myTest(){
        DefaultProcessorSlotChain defaultProcessorSlotChain = new DefaultProcessorSlotChain();
        defaultProcessorSlotChain.addNode(new MyFilter1());
        defaultProcessorSlotChain.addNode(new MyFilter2());
        defaultProcessorSlotChain.carryOn(100);
    }
}
