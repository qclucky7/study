package com.qingchen.study.state;

/**
 * @ClassName Order
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 14:00
 **/
public class Order extends AbstractState{

    private ControlEntity controlEntity;

    @Override
    void handle(ControlEntity control) {
        controlEntity = control;
        order();
    }

    @Override
    public void order() {
        System.out.println("已下单");
        controlEntity.setAbstractState(ControlEntity.stateMap.get(State.pay));
    }

    @Override
    public void pay() {
        System.out.println("还未下单");
    }

    @Override
    public void success() {
        System.out.println("还未下单");
    }

    @Override
    public void take() {
        System.out.println("还未下单");
    }
}
