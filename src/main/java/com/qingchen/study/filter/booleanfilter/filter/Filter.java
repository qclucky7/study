package com.qingchen.study.filter.booleanfilter.filter;

/**
 * @InterfaceName Filter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:10
 **/
public interface Filter<T> {

   Object doFilter(T t, FilterChain chain);

}
