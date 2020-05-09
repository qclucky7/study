package com.qingchen.study.spring.ext;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ExtConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-05 19:04
 **/
@Configuration
public class ExtConfig {

    /**
     * 扩展原理
     * @BeanPostProcessor 后置处理器   在对象初始化前后执行这
     *
     * @BeanFactoryPostProcessor beanFactory的后置处理器
     * 在beanFactory初始化之后调用, 所有的bean定义保存加载到beanFactory, 但是实例还没创建
     *   1. ioc容器对象
     *   2. invokeBeanFactoryPostProcessor(beanFactory) 执行BeanFactoryPostProcessor
     *      如何找到执行BeanFactoryPostProcessor并执行他们的方法
     *      1. 在beanFactory找到所有类型是BeanFactoryPostProcessor的组件  遍历
     *      2. 在初始化其他组件之前执行
     *
     * @BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
     * void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;
     * 在bean的定义被加载但还没被实例化之前执行
     * 优先于BeanFactoryPostProcessor执行
     *
     * @ApplicationListener 事件监听器
     * public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
     *     void onApplicationEvent(E var1);
     * }
     *
     */
}
