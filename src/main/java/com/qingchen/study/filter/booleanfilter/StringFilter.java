package com.qingchen.study.filter.booleanfilter;

/**
 * @ClassName StringFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:15
 **/
public class StringFilter implements Filter {

    @Override
    public boolean doFilter(Role role, FilterChain chain) {
        System.out.println("StringFilter!  执行！");
        if (role.getPrivilege().equals(Privilege.admin)){
            System.out.println("是管理员直接return");
            return true;
        }
       return chain.doFilter(role);
    }
}
