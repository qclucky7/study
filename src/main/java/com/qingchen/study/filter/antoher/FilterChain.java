package com.qingchen.study.filter.antoher;

import java.util.List;

/**
 * @ClassName FilterChain
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 14:18
 **/
public class FilterChain {

    private MyFilter currentFilter;
    private FilterChain next;
    private List<MyFilter> filters;

    public MyFilter getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(MyFilter currentFilter) {
        this.currentFilter = currentFilter;
    }

    public List<MyFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<MyFilter> filters) {
        this.filters = filters;
    }

    public FilterChain getNext() {
        return next;
    }

    public void setNext(FilterChain next) {
        this.next = next;
    }

    /**
     *
     * 递归实现责任链
     */
    public FilterChain(MyFilter myFilter) {
        this.currentFilter = myFilter;
    }


    /**
     *
     * 模拟 SpringBoot Jetty 中的 fiterChain 责任链实现机制
     */
    public FilterChain(List<MyFilter> filters) {
        if (filters.size() > 0) {
            this.currentFilter = filters.get(0);
            filters.remove(0);
            this.next = new FilterChain(filters);
        }
    }

    public void doFilter(FilterChain filterChain) {
        MyFilter currentFilter = filterChain.getCurrentFilter();
        if (null != currentFilter) {
            currentFilter.execute(filterChain.next);
        }
    }
}

