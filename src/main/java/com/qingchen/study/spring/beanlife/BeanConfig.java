package com.qingchen.study.spring.beanlife;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName BeanConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-04 14:36
 **/
@Configuration
//指定配置文件源
@PropertySource(value = "classpath:/application.properties")
public class BeanConfig{

    // 两种方式: 1.在bean指定init和destroy方法
    //          2.实现InitializingBean和DisposableBean接口
    //          3.可以使用jsr250提供的注解 @PostConstruct @PreDestroy
    //          4.BeanPostProcessor bean的后置处理器  在bean初始化前后做一些工作
    // BeanPostProcessor初始化之前
    // 初始化: 对象创建完成赋值后  调用初始化方法
    // BeanPostProcessor初始化之后
    // 销毁方法:  单实例对象在ioc容器关闭的时候调用
    //            多实例的时候ioc容器不会处理
    //去自定义初始化和销毁的方法
    //@Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Entity entity(){
        return new Entity();
    }


    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        applicationContext.getBean(Entity.class);
    }

}
