package com.qingchen.study.testclass;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TestClass
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 11:09
 **/
@Component
public class TestClass {

    public static final String NAME = "wangchen";

    private int i = 100;

    private static final Map<String,String> MAP = new ConcurrentHashMap<>();


}
