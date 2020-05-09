package com.qingchen.study.filter.springfilter;

/**
 * @InterfaceName Filter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:10
 **/
public interface Filter {

    void doFilter(Request request, Response response, FilterChain chain);
}
