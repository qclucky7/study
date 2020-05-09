package com.qingchen.study.monitor;

/**
 * @ClassName AbstractLinkedProcessorSlot
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:26
 **/
public abstract class AbstractLinkedProcessorSlot implements ProcessorSlot{

    private AbstractLinkedProcessorSlot next = null;

    @Override
    public void handle() {
        if (next != null) {
            this.next.handle();
        }

    }


    public void handleFirst() {
       handle();
    }


    public AbstractLinkedProcessorSlot getNext() {
        return next;
    }

    public void setNext(AbstractLinkedProcessorSlot next) {
        this.next = next;
    }


}
