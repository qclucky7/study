package com.qingchen.study.observer;


public interface IActionObserver {
    default String getActionName(){
        return null;
    };
    String calculate(int notify);
}
