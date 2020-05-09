package com.qingchen.study.schedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName ScheduleConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-18 13:52
 **/
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduleExecutors());
    }

    @Bean
    public Executor scheduleExecutors(){
        return Executors.newScheduledThreadPool(10);
    }

    //这可以指定异步的线程池！！！！
    @Bean("AsyncPool")
    public Executor async(){
        return Executors.newScheduledThreadPool(10);
    }
}
