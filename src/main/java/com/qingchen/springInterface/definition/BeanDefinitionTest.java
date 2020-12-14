package com.qingchen.springInterface.definition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @ClassName BeanDefinitionTest
 * @description:
 * @author: WangChen
 * @create: 2020-09-07 14:25
 **/
@Component
public class BeanDefinitionTest implements BeanDefinitionRegistryPostProcessor, Ordered {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
