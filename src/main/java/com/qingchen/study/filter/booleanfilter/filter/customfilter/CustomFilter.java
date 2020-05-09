package com.qingchen.study.filter.booleanfilter.filter.customfilter;

import com.qingchen.study.filter.booleanfilter.filter.Filter;
import com.qingchen.study.filter.booleanfilter.filter.FilterChain;
import com.qingchen.study.filter.booleanfilter.filterEntity.Custom;
import com.qingchen.study.filter.booleanfilter.annotation.InitializeOrder;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:15
 **/
@InitializeOrder(value = 0, effective = false)
@Component
public class CustomFilter implements Filter<Custom> {

    @Override
    public Boolean doFilter(Custom custom, FilterChain chain) {
        System.out.println("custom CustomFilter!  执行！");
       return (Boolean) chain.doFilter(custom);
    }
}
