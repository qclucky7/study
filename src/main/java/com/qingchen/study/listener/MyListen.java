package com.qingchen.study.listener;

/**
 * @ClassName MyListen
 * @description:
 * @author: WangChen
 * @create: 2020-04-03 15:25
 **/
public class MyListen extends AbstractProvider{

    public static void main(String[] args) {
        ListenProvider listenProvider = new ListenProvider();
        MyListenApply myListenApply = new MyListenApply();
        listenProvider.addListen(MyListen.class, o -> {
            System.out.println("另一种写法 - -");
            return null;
        });
        listenProvider.addListen(MyListen.class, myListenApply);
        listenProvider.send(new MyListen(), myListenApply);
        listenProvider.remove(MyListen.class, myListenApply);
        listenProvider.send(new MyListen());
    }
}
