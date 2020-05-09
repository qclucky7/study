package com.qingchen.study.monitor.my;

/**
 * @InterfaceName ProcessorSlot
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:26
 **/
public interface ProcessorSlot {

    default void fireEntity(long number){}

    default void carryOn(long number){}
}