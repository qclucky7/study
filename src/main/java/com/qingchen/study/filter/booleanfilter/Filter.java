package com.qingchen.study.filter.booleanfilter;

/**
 * @InterfaceName Filter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:10
 **/
public interface Filter {

    boolean doFilter(Role role, FilterChain chain);
}
