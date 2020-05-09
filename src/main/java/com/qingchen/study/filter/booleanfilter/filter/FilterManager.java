package com.qingchen.study.filter.booleanfilter.filter;


import com.qingchen.study.filter.booleanfilter.filter.FilterChain;
import org.springframework.stereotype.Component;


/**
 * @ClassName FilterManager
 * @Author WangChen
 * @Date 2020/3/30 9:34
 */
@Component
public class FilterManager extends FilterChain {

    public FilterManager(){}

    @Override
    public boolean filterEntity(Object o) {
       return (Boolean)filter(o);
    }

}
