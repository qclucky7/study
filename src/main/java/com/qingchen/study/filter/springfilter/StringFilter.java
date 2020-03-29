package com.qingchen.study.filter.springfilter;

/**
 * @ClassName StringFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:15
 **/
public class StringFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("StringFilter!  执行！");
        chain.doFilter(request, response);
        System.out.println("StringFilter！ 逆序执行！");
    }
}
