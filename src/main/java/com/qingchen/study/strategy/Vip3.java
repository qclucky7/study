package com.qingchen.study.strategy;

import org.springframework.stereotype.Service;

@Service(value = "vip3")
public class Vip3 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("打9折");
    }

    @Override
    public void doSomething(int i) {

    }

}
