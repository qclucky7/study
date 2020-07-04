package com.qingchen.study.beancopy;

import com.qingchen.study.utils.other.HostNameUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Function;

/**
 * @ClassName BeanCopyTest
 * @description:
 * @author: WangChen
 * @create: 2020-06-26 19:54
 **/
public class BeanCopyTest {


    @Test
    public void myTest(){

        Order order = new Order(1, 1, 1, 100L, "天津", "666", "15242957219",
                new Goods(2, 2, 2, "衣服", "xxx.com"));

        OrderVO orderVO = new OrderVO();

        GoodsVO goodsVO = new GoodsVO();

        orderVO.setGoods(goodsVO);

        BeanUtils.copyProperties(order, orderVO);
        BeanUtils.copyProperties(order.getGoods(), goodsVO);

        System.out.println(orderVO);

    }

    @Test
    public void myTest2(){

        String configString = HostNameUtil.getConfigString();

        System.out.println(HostNameUtil.getHostName());
        System.out.println(HostNameUtil.getIp());
    }

    @Test
    public void myTest3(){

        Order order = new Order(1, 1, 1, 100L, "天津", "666", "15242957219",
                new Goods(2, 2, 2, "衣服", "xxx.com"));

    }


    @Test
    public void myTest4(){

        Order order = new Order(1, 1, 1, 100L, "天津", "666", "15242957219",
                new Goods(2, 2, 2, "衣服", "xxx.com"));


        OrderVO orderVO = BeanCopyUtils.copyProperties(order, OrderVO::new);

        System.out.println(orderVO);


    }
}
