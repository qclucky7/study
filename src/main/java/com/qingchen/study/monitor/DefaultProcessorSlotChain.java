package com.qingchen.study.monitor;

/**
 * @ClassName DefaultProcessorSlotChain
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 14:31
 **/
public class DefaultProcessorSlotChain extends AbstractProcessorSlot{

    private AbstractLinkedProcessorSlot first =  new AbstractLinkedProcessorSlot(){

        @Override
        public void handle() {
            super.handle();
        }
    };

    AbstractLinkedProcessorSlot end = first;

    @Override
    public void addLast(AbstractLinkedProcessorSlot abstractLinkedProcessorSlot) {
        end.setNext(abstractLinkedProcessorSlot);
        end = abstractLinkedProcessorSlot;
    }

    @Override
    public void setNext(AbstractLinkedProcessorSlot next) {
        addLast(next);
    }

    @Override
    public AbstractLinkedProcessorSlot getNext() {
        return first.getNext();
    }

    @Override
    public void handle() {
        first.handleFirst();
    }
}
