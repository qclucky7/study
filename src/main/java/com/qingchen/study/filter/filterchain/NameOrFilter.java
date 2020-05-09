package com.qingchen.study.filter.filterchain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName NameFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 16:23
 **/
public class NameOrFilter implements RuleFilter<Rule> {

    @Override
    public List<Rule> filterList(List<Rule> list, FilterChain chain, int pos) {
        System.out.println("执行了NameOrFilter");
        List<Rule> collect = list.stream().filter(rule -> rule.getName().length() > 5).collect(Collectors.toList());
        /*if (lastFilter(chain, pos)){
            System.out.println("这是最后一个过滤器");
            return collect;
        }*/
        return chain.filterList(collect);
    }
}
