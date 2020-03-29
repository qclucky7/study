package com.qingchen.study.state;

/**
 * @ClassName Success
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 14:09
 **/
public class Success extends AbstractState{

    private ControlEntity controlEntity;

    @Override
    void handle(ControlEntity control) {
        controlEntity = control;
        success();
    }

    @Override
    public void order() {
        System.out.println("请下单");
    }

    @Override
    public void pay() {
        System.out.println("请下单");
    }

    @Override
    public void success() {
        System.out.println("支付成功");
        controlEntity.setAbstractState(ControlEntity.stateMap.get(State.take));
    }

    @Override
    public void take() {
        System.out.println("支付失败");
    }
}
