package com.qingchen.study.observer.common;

import com.qingchen.study.observer.IActionObserver;
import com.qingchen.study.observer.IActionSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("activitySubject")
public class ActivitySubject implements IActionSubject {
    private Logger logger = LoggerFactory.getLogger(ActivitySubject.class);

    /**
     * 观察者集合
     */
    private List<IActionObserver> observers = new ArrayList<IActionObserver>();

    @Override
    public void registerObserver(IActionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IActionObserver observer) {
        if (observers.indexOf(observer) >= 0) {
            observers.remove(observer);
        }
    }

    @Override
    public List<String> notifyObservers(int notify) {
        List<String> list = new ArrayList<>(2);
        System.out.println("observers list =" + observers.toString());
        for (IActionObserver ob : observers) {
            logger.debug("ActionObserver = {}", ob.getActionName());
            list.add(ob.calculate(notify));
        }
        return list;
    }
}
