package com.qingchen.study.collection;

import com.google.common.io.CharStreams;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName Map
 * @description:
 * @author: WangChen
 * @create: 2020-04-08 09:11
 **/
public class TestMap {



     private List list = Collections.synchronizedList(new ArrayList<>());


     @Test
     public void myTest(){

          Object o = new Object();
          System.out.println(Arrays.toString(o.toString().getBytes()));
     }

     //解决hash冲突
     // 1. 开放寻址法,  当前hash值冲突后, 用当前hash值在进行一次hash操作, 以此类推。
     // 2. 连地址法  hash冲突 , 维护一个链表结构 每次寻找去遍历链表
}
