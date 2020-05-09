package com.qingchen.study.monitor;

/**
 * @ClassName ProcessorSlot1
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:43
 **/
public class ProcessorSlot1 extends AbstractLinkedProcessorSlot{

    @Override
    public void handle() {
        System.out.println("处理链路1");
        super.handle();
    }
}
