package com.qingchen.study.singleton;

/**
 * @ClassName Singleton
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 14:20
 **/
public class Singleton {

    private static class SingletonProvider {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonProvider.instance;
    }


    public static void main(String[] args) {

        System.out.println(Singleton.getInstance() == Singleton.getInstance());
    }

}
