package com.qingchen.study.filter.booleanfilter.filterEntity;

import com.qingchen.study.filter.booleanfilter.common.Privilege;

/**
 * @ClassName Role
 * @description:
 * @author: WangChen
 * @create: 2020-03-29 14:22
 **/
public class Role {

    private Privilege privilege;


    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
