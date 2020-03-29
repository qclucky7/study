package com.qingchen.study.filter.booleanfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * @ClassName FilterChain
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:11
 **/
public class FilterChain {

    private EnumSet<Privilege> enumSet = EnumSet.allOf(Privilege.class);

    private List<Filter> filters;

    private int index;

    public void addFilter(Filter... filter) {
        filters = new ArrayList<>(filter.length);
        filters.addAll(Arrays.asList(filter));
    }

    public boolean doFilter(Role role){

        if (index < filters.size()){
            Filter filter = filters.get(index);
            index++;
            return filter.doFilter(role, this);
        }
        return false;
    }

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new StringFilter(),new URLFilter());

        Role role = new Role();
        role.setPrivilege(Privilege.visiter);

        boolean b = filterChain.doFilter(role);

        System.out.println(b);

        System.out.println(Arrays.toString(filterChain.enumSet.toArray()));

        System.out.println(1<<10);

    }
}
