package com.qingchen.study.monitor;

/**
 * @ClassName AbstractLinkedProcessorSlot
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:26
 **/
public abstract class AbstractProcessorSlot extends AbstractLinkedProcessorSlot{

    abstract void addLast(AbstractLinkedProcessorSlot abstractLinkedProcessorSlot);
}
