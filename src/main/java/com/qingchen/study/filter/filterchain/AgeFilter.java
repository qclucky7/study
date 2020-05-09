package com.qingchen.study.filter.filterchain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AgeFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 16:26
 **/
public class AgeFilter implements RuleFilter<Rule> {

    @Override
    public List<Rule> filterList(List<Rule> list, FilterChain chain, int pos) {
        System.out.println("执行了age");
        List<Rule> collect = list.stream().filter(rule -> rule.getAge() > 20).collect(Collectors.toList());
        System.out.println("age= " + collect.toString());
        if (lastFilter(chain, pos)){
            return collect;
        }
        return chain.filterList(collect);
    }
}
