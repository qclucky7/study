package com.qingchen.study.observer.common;

import com.qingchen.study.observer.IActionObserver;
import org.springframework.stereotype.Service;

@Service(value = "vip2")
public class Vip2 implements IActionObserver {
    @Override
    public String getActionName() {

        return this.getClass().getName();
    }

    @Override
    public String calculate(int notify) {
        if (10 == notify){
            return "vip2 已经通知！";
        }
        return "";
    }
}
