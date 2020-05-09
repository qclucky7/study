package com.qingchen.study.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLock
 * @description:
 * @author: WangChen
 * @create: 2020-04-18 13:02
 **/
@Configuration
@EnableScheduling
public class RedisLockTest implements SchedulingConfigurer {

    @Autowired
    private RedisLock redisLock;

    //3.添加定时任务
    //@Async
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public boolean testRedisLock() throws Exception{
        boolean testRedisLock = redisLock.lock("test_redis_lock", System.currentTimeMillis());
        //redisLock.lock("test_redis_lock", System.currentTimeMillis(), 5, TimeUnit.SECONDS);
        System.out.println("方法一线程 = " + Thread.currentThread().getName());
        System.out.println("方法一锁 = " + testRedisLock);
        if (!testRedisLock){
            System.out.println("方法1没拿到锁！！老铁");
           return false;
        }
        System.out.println("方法一拿到锁了！！");
        TimeUnit.SECONDS.sleep(10);
        redisLock.unlock("test_redis_lock", null);
        return true;
    }

    //3.添加定时任务
    //@Async
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public boolean testRedisLock1() throws Exception{
        boolean testRedisLock = redisLock.lock("test_redis_lock", System.currentTimeMillis());
        System.out.println("方法二线程 = " + Thread.currentThread().getName());
        System.out.println("方法二锁 = " + testRedisLock);
        if (!testRedisLock){
            System.out.println("方法2没拿到锁！！老铁");
            return false;
        }
        System.out.println("方法二拿到锁了！！");
        TimeUnit.SECONDS.sleep(10);
        redisLock.unlock("test_redis_lock", null);
        return true;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(15); //指定线程池大小
    }

}
