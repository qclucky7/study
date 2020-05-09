package com.qingchen.study.monitor.my;

/**
 * @ClassName AbstractLinkedProcessorSlot
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:26
 **/
public abstract class AbstractLinkedProcessorSlot implements ProcessorSlot {

    private AbstractLinkedProcessorSlot next = null;

    @Override
    public void fireEntity(long number) {
        if (next != null){
            next.transformEntry(number);
        }
    }

    public void transformEntry(long number){
        carryOn(number);
    }

    public AbstractLinkedProcessorSlot getNext() {
        return next;
    }

    public void setNext(AbstractLinkedProcessorSlot next) {
        this.next = next;
    }

    public void addNode(AbstractLinkedProcessorSlot node) {

    }


}
