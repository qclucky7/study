package com.qingchen.study.state;

/**
 * @ClassName Take
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 14:09
 **/
public class Take extends AbstractState {

    private ControlEntity controlEntity;

    @Override
    void handle(ControlEntity control) {
        controlEntity = control;
        take();
    }

    @Override
    public void order() {
        System.out.println("请先下单");
    }

    @Override
    public void pay() {
        System.out.println("请先下单");
    }

    @Override
    public void success() {
        System.out.println("请先下单");
    }

    @Override
    public void take() {
        System.out.println("收获成功");
    }
}
