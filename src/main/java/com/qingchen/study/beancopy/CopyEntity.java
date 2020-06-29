package com.qingchen.study.beancopy;

import java.util.List;

/**
 * @ClassName CopyEntity
 * @description:
 * @author: WangChen
 * @create: 2020-06-29 09:40
 **/
public class CopyEntity {

    private List<Order> orders;

    public CopyEntity(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
