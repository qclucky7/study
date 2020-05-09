package com.qingchen.study.observer.common;

import com.qingchen.study.observer.IActionObserver;
import org.springframework.stereotype.Service;

@Service(value = "vip1")
public class Vip1 implements IActionObserver {
    @Override
    public String getActionName() {
        return this.getClass().getName();
    }

    @Override
    public String calculate(int notify) {
        if (5 <= notify){
            return "vip1 已经通知！";
        }
        return "";

    }
}
