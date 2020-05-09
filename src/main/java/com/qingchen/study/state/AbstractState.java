package com.qingchen.study.state;

/**
 * @ClassName AbstractState
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 13:59
 **/
public abstract class AbstractState implements Behavior{

    abstract void handle(ControlEntity controlEntity);
}
