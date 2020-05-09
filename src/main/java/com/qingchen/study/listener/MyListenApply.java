package com.qingchen.study.listener;

/**
 * @ClassName MyListenApply
 * @description:
 * @author: WangChen
 * @create: 2020-04-03 15:26
 **/
public class MyListenApply implements Listen<MyListen>{

    @Override
    public Object call(MyListen myListen) {

        System.out.println("监听器回调！");

        return null;
    }
}
