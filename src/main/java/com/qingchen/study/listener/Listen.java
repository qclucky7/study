package com.qingchen.study.listener;

/**
 * @ClassName Listen
 * @description:
 * @author: WangChen
 * @create: 2020-04-03 14:24
 **/
public interface Listen<T> {

    Object call(T t);
}
