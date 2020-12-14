//package com.qingchen.springInterface.factory;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName TestOrder
// * @description:
// * @author: WangChen
// * @create: 2020-09-07 14:15
// **/
//@Component
//public class TestOrder implements BeanPostProcessor, InitializingBean, BeanDefinitionRegistryPostProcessor {
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("------------------>InitializingBean");
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        System.out.println("------------------>postProcessBeanFactory");
//    }
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        System.out.println("------------------>postProcessBeanDefinitionRegistry");
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("------------------>postProcessBeforeInitialization");
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("------------------>postProcessAfterInitialization");
//        return bean;
//    }
//}
