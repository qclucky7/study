package com.qingchen.study.filter.booleanfilter.filter.customfilter;

import com.qingchen.study.filter.booleanfilter.filter.Filter;
import com.qingchen.study.filter.booleanfilter.filter.FilterChain;
import com.qingchen.study.filter.booleanfilter.common.Privilege;
import com.qingchen.study.filter.booleanfilter.filterEntity.Role;
import com.qingchen.study.filter.booleanfilter.annotation.InitializeOrder;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:15
 **/
@InitializeOrder(value = 0)
@Component
public class StringFilter implements Filter<Role> {

    @Override
    public Boolean doFilter(Role role, FilterChain chain) {
        System.out.println("StringFilter!  执行！");
        if (role.getPrivilege().equals(Privilege.admin)){
            System.out.println("是管理员直接return");
            return true;
        }
       return (Boolean) chain.doFilter(role);
    }
}
