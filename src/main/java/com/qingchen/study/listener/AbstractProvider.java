package com.qingchen.study.listener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName IProvider
 * @description:
 * @author: WangChen
 * @create: 2020-04-03 14:27
 **/
public abstract class AbstractProvider implements IProvider{

    protected static Map<Class<? extends AbstractProvider>, Set<Listen>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void addListen(Class<? extends AbstractProvider> clazz, Listen listen){
        Set<Listen> listens = cacheMap.getOrDefault(clazz, new HashSet<>());
        listens.add(listen);
        cacheMap.put(clazz, listens);
    };

    @Override
    public void remove(Class<? extends AbstractProvider> clazz, Listen listen){
        Set<Listen> listens = cacheMap.getOrDefault(clazz, new HashSet<>());
        listens.removeIf(listen1 -> listen1.equals(listen));
        cacheMap.put(clazz, listens);
    }

    @Override
    public void send(Object o){
        Set<Listen> listens = cacheMap.getOrDefault(o.getClass(), Collections.emptySet());
        System.out.println(listens.toString());
        for (Listen listen : listens) {
            listen.call(o);
        }
    }

    public void send(Object o, Listen... listeners){
        Set<Listen> listens = cacheMap.getOrDefault(o.getClass(), Collections.emptySet());
        for (Listen listen : listens) {
            for (Listen listener : listeners) {
                if (listen.equals(listener)){
                    listen.call(o);
                }
            }
        }
    }
}
