package com.qingchen.study.schedconfig;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName ScheduleTask
 * @description:
 * @author: WangChen
 * @create: 2020-04-18 13:57
 **/
@Component
@EnableScheduling
public class ScheduleTask {

    //第一种指定线程池！
    @Scheduled(cron = "0/5 * * * * ?")
    public void task(){

    }

    //第二种指定线程池！  去指定async的线程池！！！
    @Async("AsyncPool")
    @Scheduled(cron = "0/5 * * * * ?")
    public void task2(){

    }
}
