package com.qingchen.study.listener;

/**
 * @InterfaceName IProvider
 * @description:
 * @author: WangChen
 * @create: 2020-04-03 15:50
 **/
public interface IProvider {

    void addListen(Class<? extends AbstractProvider> clazz, Listen listen);

    void remove(Class<? extends AbstractProvider> clazz, Listen listen);

    void send(Object o);
}
