package com.qingchen.study.filter.springfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FilterChain
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:11
 **/
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    private int index;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response){

        if (index < filters.size()){
            Filter filter = filters.get(index);
            index++;
            filter.doFilter(request, response, this);
        }

    }

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new StringFilter())
                .addFilter(new URLFilter());

        filterChain.doFilter(new Request(), new Response());

    }
}
