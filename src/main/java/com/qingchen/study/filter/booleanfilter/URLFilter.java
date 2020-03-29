package com.qingchen.study.filter.booleanfilter;

/**
 * @ClassName URLFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:17
 **/
public class URLFilter  implements Filter {

    @Override
    public boolean doFilter(Role role, FilterChain chain) {
        System.out.println("URLFilter 执行！");
        if (role.getPrivilege().equals(Privilege.user)){
            System.out.println("都可以执行！！");
            return true;
        }
        return chain.doFilter(role);
    }
}
