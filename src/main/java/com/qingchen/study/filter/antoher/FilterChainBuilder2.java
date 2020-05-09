package com.qingchen.study.filter.antoher;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @ClassName 啊
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 14:20
 **/
public class FilterChainBuilder2 {

    public static FilterChain buildFilterChain(List<MyFilter> filters) {

        if (CollectionUtils.isEmpty(filters)) {
            return null;
        }
        MyFilter currentFilter = filters.get(0);
        FilterChain filterChain2 = new FilterChain(currentFilter);
        filters.remove(0);

        if (filters.size() > 0) {
            filterChain2.setNext(buildFilterChain(filters));
        }
        return filterChain2;
    }
}
