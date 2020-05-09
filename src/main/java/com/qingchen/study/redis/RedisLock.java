package com.qingchen.study.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLock
 * @description:
 * @author: WangChen
 * @create: 2020-04-18 13:09
 **/
@Component
public class RedisLock {

    /**默认过期10秒**/
    private static final int EXPIRE = 10;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public Boolean lock(String key, Object value){
         return this.lock(key, value, EXPIRE, TIME_UNIT);
    }

    public Boolean lock(String key, Object value, long expire, TimeUnit timeUnit){
        Boolean statue = redisTemplate.opsForValue().setIfAbsent(key, value, expire, timeUnit);
        if (statue == null){
            return false;
        }
        return statue;
    }

    public Boolean unlock(String key, Object value){
        //这不是原子性操作  还是不行   用lua脚本
        //检查这个锁是不是自己的锁
        //Object obj = redisTemplate.opsForValue().get(key);
//        if (value.equals(obj)){
//        }
        Boolean statue = redisTemplate.delete(key);
        if (statue == null){
            return false;
        }
        return statue;
    }
}
