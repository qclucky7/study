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



        Goods goods = new Goods("衣服", "xxx.com");
        GoodsVO goodsVO = BeanCopyUtils.copyProperties(goods, GoodsVO::new,
                (good, goodVO) -> goodVO.setGoodName(good.getGoodName() + "裤子"));

        System.out.println(goodsVO);


    }

    @Test
    public void myTest2(){

        String configString = HostNameUtil.getConfigString();

        System.out.println(HostNameUtil.getHostName());
        System.out.println(HostNameUtil.getIp());
    }

    @Test
    public void myTest3(){

        List<Integer> list = new ArrayList<>();

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);

        List<Integer> integers2 = Arrays.asList(1, 2, 3, 4);

        list.addAll(integers);
        list.addAll(integers2);

        System.out.println(list.toString());

    }


    @Test
    public void myTest4(){

        Order order = new Order(1, 1, 1, 100L, "天津", "666", "15242957219",
                new Goods("衣服", "xxx.com"));


        OrderVO orderVO = BeanCopyUtils.copyProperties(order, OrderVO::new);

        System.out.println(orderVO);


    }
}
