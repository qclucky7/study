package com.qingchen.study.filter.booleanfilter.filter;

import com.qingchen.study.application.ApplicationContextProvider;
import com.qingchen.study.spring.myconfig.MyConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @ClassName SpringGetInterface
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 15:59
 **/
@Component
public class SpringGetInterface {


    static {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        Map<String, Filter> beansOfType = applicationContext.getBeansOfType(Filter.class);
        System.out.println("接口实现类 = " +beansOfType.toString());
    }

    @PostConstruct
    public void load(){
//        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
//        Map<String, Filter> beansOfType = applicationContext.getBeansOfType(Filter.class);
//        System.out.println("接口实现类 = " +beansOfType.toString());
    }
}
