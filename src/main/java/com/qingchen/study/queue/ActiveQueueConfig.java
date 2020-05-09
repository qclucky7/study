package com.qingchen.study.queue;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ActiveQueueConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-26 19:35
 **/
@Configuration
public class ActiveQueueConfig {

    @Bean
    public ActiveQueue activeQueue(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                10,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(256),
                new DefaultThreadFactory("active-queue")
        );
        ActiveQueue activeQueue = new ActiveQueue();
        activeQueue.executor = threadPoolExecutor;
        return activeQueue;
    }
}
