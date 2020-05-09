package com.qingchen.study.monitor;

/**
 * @ClassName ProcessorSlot2
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:44
 **/
public class ProcessorSlot2 extends AbstractLinkedProcessorSlot{
    @Override
    public void handle() {
        System.out.println("处理链路2");
        super.handle();
    }
}
