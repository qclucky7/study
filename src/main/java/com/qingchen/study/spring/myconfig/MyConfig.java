package com.qingchen.study.spring.myconfig;

import org.springframework.context.annotation.*;

/**
 * @ClassName MyConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-04 11:22
 **/
@Configuration
//@ComponentScan(value = "com.qingchen.study") 包扫描
//@ComponentScan(value = "com.qingchen.study", excludeFilters = {  可以指定排除或者指定扫描的包
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
//})
//@ComponentScan(value = "com.qingchen.study", includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
//}, useDefaultFilters = false //不使用默认策略
//)
//@Import(value = {User.class})  导入组件  id默认是全类名
//@Import(value = {ImportSelect.class}) 自定义扫描选择器去加载bean
public class MyConfig {

    /**
     * scope 默认是单例的 Singleton  在ioc容器创建的时候就存到ioc容器中
     *       prototype 多例的 ioc容器创建时不会去调用对象创建方法放到容器中,只有当获取这个对象的时候才去创建这个对象
     *       request 一次请求一个实例
     *       session  一个会话一个实例
     *
     * 懒加载Lazy
     * 单例bean会在ioc容器创建的时候去创建这个对象加到容器里面
     * Lazy会在第一次获取这个对象的时候才会去初始化这个对象放到ioc容器
     * @return
     */
    //@Conditional(value = {TestCondition.class}) 可以根据条件指定加载的bean
    //也可以写在类上统一设置需要加载的bean
//    @Lazy
//    @Scope
//    public User user(){ //方法名是在ioc容器里面的名字
//        return new User("王晨", 24);
//    }

    /**
     * 给ioc容器添加组件的方式
     * 1. 使用包扫描加注解
     * 2. 使用@bean
     * 3. @import
     *        1. @Import  id默认为类的全类名
     *        2. ImportSelector
     *        3. ImportBeanDefinitionRegistrar 手动注册bean到容器
     * 4. 使用FactoryBean
     */

}
