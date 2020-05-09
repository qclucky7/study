package com.qingchen.study.state;

/**
 * @ClassName Pay
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 14:04
 **/
public class Pay extends AbstractState {

    private ControlEntity controlEntity;

    @Override
    void handle(ControlEntity control) {
        controlEntity = control;
        pay();
    }

    @Override
    public void order() {
        System.out.println("请先下单！");
    }

    @Override
    public void pay() {
        System.out.println("已支付");
        controlEntity.setAbstractState(ControlEntity.stateMap.get(State.success));
    }

    @Override
    public void success() {
        System.out.println("请先支付");
    }

    @Override
    public void take() {
        System.out.println("请先支付");
    }
}
