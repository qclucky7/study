package com.qingchen.study.monitor;

import org.junit.Test;

/**
 * @ClassName ProcessorSlotTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:45
 **/
public class ProcessorSlotTest {


    @Test
    public void myTest(){

        DefaultProcessorSlotChain defaultProcessorSlotChain = new DefaultProcessorSlotChain();
        defaultProcessorSlotChain.addLast(new ProcessorSlot2());
        defaultProcessorSlotChain.addLast(new ProcessorSlot2());
        defaultProcessorSlotChain.addLast(new ProcessorSlot2());
        defaultProcessorSlotChain.addLast(new ProcessorSlot1());
        defaultProcessorSlotChain.handle();

    }
}
