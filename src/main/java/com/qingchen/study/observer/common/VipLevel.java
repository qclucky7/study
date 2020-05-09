package com.qingchen.study.observer.common;

public enum VipLevel {

    VIP_LEVEL_5(1),

    VIP_LEVEL_10(2);


    private int initialCapacity;

    VipLevel(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public int getInitialCapacity(){
        return initialCapacity;
    }
}
