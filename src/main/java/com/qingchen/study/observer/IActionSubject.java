package com.qingchen.study.observer;

import java.util.List;


public interface IActionSubject {
    // 添加观察者
    void registerObserver(IActionObserver observer);

    // 删除观察者
    void removeObserver(IActionObserver observer);

    // 通知所有的观察者
    List<String> notifyObservers(int notify);
}
