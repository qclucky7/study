package com.qingchen.study.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Map
 * @description:
 * @author: WangChen
 * @create: 2020-04-08 09:11
 **/
public class TestMap {

     private static Map map =  new ConcurrentHashMap<>();
     private static Map hashMap =  new HashMap();

     static {
          hashMap.put(1,1);
          hashMap.put(2,2);
          hashMap.put(3,3);
          hashMap.put(4,4);
          hashMap.put(5,5);
          hashMap.put(6,6);
          hashMap.put(7,7);
     }

     @Test
     public void testMap(){
          hashMap.put(8,8);
     }

     //解决hash冲突
     // 1. 开放寻址法,  当前hash值冲突后, 用当前hash值在进行一次hash操作, 以此类推。
     // 2. 连地址法  hash冲突 , 维护一个链表结构 每次寻找去遍历链表
}
