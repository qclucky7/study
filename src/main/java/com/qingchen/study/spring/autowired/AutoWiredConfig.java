package com.qingchen.study.spring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AutoWiredConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-04 15:25
 *
 * @Autowired自动注入
 * @默认按类型去容器里面找getBean(XXX.class)
 * @如果找到多个类,在按照名称取找getBean("name")
 * @Qualifier("myDao")可以指定需要装配的属性id而不是属性名
 * @自动装配一定是赋值好的,如果找不到就报错
 * @可以指定@Autowired(require=false)没有注入的组件是null;
 * @可以在bean上加@Primary注解,这样每次都是注入的时候都是首选这个bean
 *
 * @Resource这是java规范的注解不支持@Primary和@Qualifier
 * @默认是按照名称去装配
 *
 * @Autowired标记在set方法 {@link MyDao#setUser}{@link MyDao#MyDao}
 *
 **/
@Configuration
public class AutoWiredConfig {

    //@Qualifier("myDao")
    @Autowired(required = false)
    private MyDao myDao;

    @Primary
    @Bean("myDao2")
    public MyDao myDao(){
        //return new MyDao();
        return null;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoWiredConfig.class);
        MyDao bean = context.getBean(MyDao.class);

        System.out.println(bean.getClass().getName());
    }
}
