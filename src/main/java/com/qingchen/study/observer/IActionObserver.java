package com.qingchen.study.observer;

public interface IActionObserver {
    String getActionName();
    String calculate(int notify);
}
