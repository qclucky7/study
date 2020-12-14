package com.qingchen.springInterface.beanpost;

import com.qingchen.study.ratelimit.RateLimit;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangChen
 * @since 2020-10-29 17:35
 **/
public abstract class AbstractBeanPostTest implements BeanPostProcessor, InitializingBean{

    private List<Object> list = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        //System.out.println("AbstractBeanPostTest postProcessAfterInitialization bean = " + bean.getClass().getName());
        if (bean.getClass().isAnnotationPresent(Controller.class)){
            System.out.println("AbstractBeanPostTest bean = " + bean.getClass().getName());
            list.add(bean);
        }
        return null;
    }


    public List<Object> getAll(){
        return list;
    }
}
