package com.qingchen.springInterface;

import com.qingchen.study.filter.booleanfilter.annotation.InitializeOrder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitBean
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 09:14
 **/
@Component
public class BeanPostProcessorTest implements BeanPostProcessor{


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(InitializeOrder.class)){
            System.out.println("InitializeOrder注解" + bean.toString() + "-----" + beanName);
        }
        return bean;
    }
}
