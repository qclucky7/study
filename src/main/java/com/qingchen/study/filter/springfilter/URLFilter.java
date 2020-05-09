package com.qingchen.study.filter.springfilter;

/**
 * @ClassName URLFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:17
 **/
public class URLFilter  implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("URLFilter 执行！");
        chain.doFilter(request, response);
        System.out.println("URLFilter 逆序执行！");
    }
}
