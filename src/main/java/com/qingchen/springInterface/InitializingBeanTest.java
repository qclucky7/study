package com.qingchen.springInterface;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName InitializingBeanTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 09:40
 **/
@Component
public class InitializingBeanTest implements InitializingBean {

    private List<BeanPostProcessorTest> list = new ArrayList<>();

    @Autowired
    private BeanPostProcessorTest beanPostProcessorTest;

    @Override
    public void afterPropertiesSet() throws Exception {
        list.add(beanPostProcessorTest);

        System.out.println("InitializingBean = " + list.toString());
    }
}
