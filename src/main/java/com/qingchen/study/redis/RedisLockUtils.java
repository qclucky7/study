package com.qingchen.study.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLockUtils
 * @description:
 * @author: WangChen
 * @create: 2020-04-19 20:05
 **/
@Component
public class RedisLockUtils {

    private Logger log = LoggerFactory.getLogger(RedisLockUtils.class);

    private RedisLockUtils(){}

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    /** 获取分布式锁
     * @param lockKey   key
     * @param requestId 唯一id
     * @param expire    时间值
     * @param timeUnit  时间单位
     * @return true:加锁成功，false:已有人获取锁
     * */
    public boolean tryLock(String lockKey, String requestId, long expire, TimeUnit timeUnit) {

        Boolean statue = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expire, timeUnit);
        if (statue == null){
            return false;
        }
        return statue;
//        try {
//            RedisCallback<Boolean> callback = (connection) -> connection.set(
//                    lockKey.getBytes(StandardCharsets.UTF_8),
//                    requestId.getBytes(StandardCharsets.UTF_8),
//                    Expiration.seconds(timeUnit.toSeconds(expire)),
//                    RedisStringCommands.SetOption.SET_IF_ABSENT);
//            return (Boolean) redisTemplate.execute(callback);
//        } catch (Exception e) {
//            log.error("redis lock error.", e);
//        }
    }

    /** 释放锁
     *  @param lockKey   key
     *  @param requestId 唯一id
     *  @return true:释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) {

        RedisCallback<Boolean> callback = (connection) -> connection.eval(
                UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1,
                lockKey.getBytes(StandardCharsets.UTF_8),
                requestId.getBytes(StandardCharsets.UTF_8));
        Boolean execute = redisTemplate.execute(callback);
        if (execute == null){
            return false;
        }
        return execute;

    }



}

