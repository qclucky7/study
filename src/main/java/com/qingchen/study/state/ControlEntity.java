package com.qingchen.study.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ControlEntity
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 13:59
 **/
public class ControlEntity {

    private AbstractState abstractState;

    protected static Map<State, AbstractState> stateMap = new HashMap<>();

    public ControlEntity() {
        abstractState = new Order();
        init();
    }

    private static void init(){
        stateMap.put(State.order, new Order());
        stateMap.put(State.pay, new Pay());
        stateMap.put(State.success, new Success());
        stateMap.put(State.take, new Take());
    }

    public void setAbstractState(AbstractState abstractState) {
        this.abstractState = abstractState;
    }

    public void handle(){
        abstractState.handle(this);
    }

    public static void main(String[] args) {
        ControlEntity controlEntity = new ControlEntity();
        controlEntity.setAbstractState(new Pay());
        System.out.println(ControlEntity.stateMap.toString());
        controlEntity.handle();
        controlEntity.handle();
        controlEntity.handle();
        controlEntity.handle();
    }


}
