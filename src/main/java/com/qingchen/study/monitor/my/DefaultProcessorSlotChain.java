package com.qingchen.study.monitor.my;

/**
 * @ClassName DefaultProcessorSlotChain
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 14:34
 **/
public class DefaultProcessorSlotChain extends AbstractLinkedProcessorSlot {

    //头节点
    private AbstractLinkedProcessorSlot headNode = new AbstractLinkedProcessorSlot(){

        @Override
        public void carryOn(long number) {
            fireEntity(number);
        }

    };

    AbstractLinkedProcessorSlot endNode = headNode;

    @Override
    public void addNode(AbstractLinkedProcessorSlot node) {
        endNode.setNext(node);
        endNode = node;
    }

    @Override
    public void carryOn(long number) {
        headNode.transformEntry(number);
    }
}
