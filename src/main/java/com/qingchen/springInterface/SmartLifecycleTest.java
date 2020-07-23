package com.qingchen.springInterface;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmartLifecycleTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-23 17:08
 **/
@Component
public class SmartLifecycleTest implements SmartLifecycle {

    @Override
    public void start() {
        System.out.println("SmartLifecycle start");
    }

    @Override
    public void stop() {
        System.out.println("SmartLifecycle stop");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
