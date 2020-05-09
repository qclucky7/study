package com.qingchen.study.filter.filterchain;

import java.util.List;

/**
 * @InterfaceName Ifliter
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 16:31
 **/
public interface RuleFilter<T> {

    List<T> filterList(List<T> list, FilterChain filterChain, int pos);

    default boolean lastFilter(FilterChain filterChain, int pos){
        if (pos == filterChain.pos){
            return true;
        }
        return false;
    }

}
