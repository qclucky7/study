package com.qingchen.study.spring.beanlife;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName Entity
 * @description:
 * @author: WangChen
 * @create: 2020-04-04 14:37
 **/
public class Entity {


    /**
     * @value使用
     * 1. 直接赋值
     * 2. SpEL表达式 #{}
     * 3. 取配置文件中的值 ${}
     */

    @Value("${nickname}")
    //@Value("王晨")
    private String name;
    @Value("#{10-2}")
    private long age;




    public Entity() {

    }

    public void init(){
        System.out.println("初始化方法！");
    }

    public void destroy(){
        System.out.println("这是销毁方法！");
    }
}
