package com.qingchen.springInterface.autowired;

import org.springframework.stereotype.Component;

/**
 * @ClassName EmptyImpl1
 * @description:
 * @author: WangChen
 * @create: 2020-07-23 16:49
 **/
@Component
public class EmptyImpl1 implements IEmpty{
    @Override
    public void exec() {
        System.out.println("EmptyImpl1");
    }
}
