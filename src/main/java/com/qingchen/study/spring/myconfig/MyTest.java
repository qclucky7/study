package com.qingchen.study.spring.myconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName MyTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-04 11:27
 **/
public class MyTest {

    public static void main(String[] args) {

        //用注解驱动获得ioc容器里面注册的值
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        User user = applicationContext.getBean(User.class);
        System.out.println(user.toString());

        String[] names = applicationContext.getBeanNamesForType(User.class);

        for (String name : names) {
            System.out.println(name);
        }

    }

}
