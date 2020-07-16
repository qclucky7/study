package com.qingchen.study.filter.booleanfilter.filter.customfilter;

import com.qingchen.study.filter.booleanfilter.filter.Filter;
import com.qingchen.study.filter.booleanfilter.filter.FilterChain;
import com.qingchen.study.filter.booleanfilter.common.Privilege;
import com.qingchen.study.filter.booleanfilter.filterEntity.Role;
import com.qingchen.study.filter.booleanfilter.annotation.InitializeOrder;
import org.springframework.stereotype.Component;

/**
 * @ClassName URLFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:17
 **/
@InitializeOrder(value = 1)
@Component
public class URLFilter implements Filter<Role> {

    @Override
    public Boolean doFilter(Role role, FilterChain chain) {
        System.out.println("URLFilter 执行！");
        if (role.getPrivilege().equals(Privilege.user)){
            return true;
        }
        return (Boolean) chain.doFilter(role);
    }
}
