package com.qingchen.study.filter.filterchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @InterfaceName IFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 16:21
 **/
public class FilterChain{

    private List<RuleFilter<Rule>> ruleFilters = new ArrayList<RuleFilter<Rule>>();

    protected int pos = 0;

    public FilterChain addRuleFilter(RuleFilter<Rule> ruleFilter) {
        ruleFilters.add(ruleFilter);
        return this;
    }

    public List<Rule> filterList(List<Rule> list) {
        if (pos < ruleFilters.size()){
            RuleFilter<Rule> ruleFilter = ruleFilters.get(pos);
            pos++;
            return ruleFilter.filterList(list, this, ruleFilters.size());
        }
        return list;
    }

    public static void main(String[] args) {

        List<Rule> rules = Arrays.asList(
                new Rule("123", 20),
                new Rule("1", 2),
                new Rule("1234", 21),
                new Rule("123", 21),
                new Rule("1232222", 21)

        );
        FilterChain filterChain = new FilterChain();
        filterChain.addRuleFilter(new AgeFilter())
                   .addRuleFilter(new NameFilter())
                   .addRuleFilter(new NameOrFilter());
        List<Rule> rules1 = filterChain.filterList(rules);

        System.out.println(rules1.toString());
    }


}
