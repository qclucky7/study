package com.qingchen.springInterface.beanpost;

import org.springframework.stereotype.Component;

/**
 * @author WangChen
 * @since 2020-10-29 17:35
 **/
@Component
public class BeanPostTest extends AbstractBeanPostTest{


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanPostTest all = " + getAll().toString());
    }
}
