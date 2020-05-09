package com.qingchen.study.filter.antoher;

/**
 * @InterfaceName MyFilter
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 14:17
 **/
public interface MyFilter {

    String getName();
    void execute(FilterChain filterChain);
}