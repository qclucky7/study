package com.qingchen.study.redis;

import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName RedisBit
 * @description:
 * @author: WangChen
 * @create: 2020-04-18 15:49
 **/
@Component
public class RedisBit {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void myTestBit(){
        //这存天数得减去1!
        redisTemplate.opsForValue().setBit("user:sign202004:100", 15, true);
        redisTemplate.opsForValue().setBit("user:sign202004:100", 16, true);
        //redisTemplate.opsForValue().setBit("12345", 20200418, true);
        redisTemplate.opsForValue().setBit("user:sign202004:100", 17, true);
        redisTemplate.opsForValue().setBit("user:sign202004:101", 15, true);
        redisTemplate.opsForValue().setBit("user:sign202004:101", 16, true);
        redisTemplate.opsForValue().setBit("user:sign202004:101", 17, true);
        redisTemplate.opsForValue().setBit("user:sign202004:101", 19, true);
    }


    public boolean mySigned(String key, long value){
        return redisTemplate.opsForValue().getBit(key, value);
    }


    public void bitField(String key){

        LocalDate now = LocalDate.now();
        HashMap<LocalDate, Boolean> signMap = new HashMap<>();
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create();
        BitFieldSubCommands to = bitFieldSubCommands.get(BitFieldSubCommands.BitFieldType.signed(30)).valueAt(0);
        List<Long> list = redisTemplate.opsForValue().bitField(key,to);
        //List<Integer> integers = Arrays.asList(20200416, 20200417, 20200418, 20200419);
        // 由低位到高位，为0表示未签，为1表示已签
        System.out.println("list = " + list);
        long v = list.get(0) == null ? 0 : list.get(0);
        for (int i = now.lengthOfMonth(); i > 0; i--) {
            LocalDate localDate = now.withDayOfMonth(i);
            signMap.put(localDate, v >> 1 << 1 != v);
            v >>= 1;
        }
        System.out.println(list.toString());
        System.out.println(signMap.toString());
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int dayOfMonth = now.getDayOfMonth();
        int i = now.lengthOfMonth();
        System.out.println(dayOfMonth);
        System.out.println(i);
    }

}
