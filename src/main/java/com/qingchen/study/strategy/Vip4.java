package com.qingchen.study.strategy;

import org.springframework.stereotype.Service;

@Service(value = "vip4")
public class Vip4 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("打8折");
    }

    @Override
    public void doSomething(int i) {

    }
}
