package com.qingchen.study.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName application
 * @description:
 * @author: WangChen
 * @create: 2020-04-02 10:40
 **/
@Configuration
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;

    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static String getProfile(){
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }


}
