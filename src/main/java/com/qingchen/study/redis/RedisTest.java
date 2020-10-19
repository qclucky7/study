package com.qingchen.study.redis;

import com.qingchen.study.spring.myconfig.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @ClassName RedisTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-16 13:54
 **/
@RestController
@RequestMapping("/redis")
public class RedisTest {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisZSet redisZSet;

    @Autowired
    private RedisBit redisBit;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/user/{id}")
    public void test(@PathVariable("id") Integer id){
        User user = new User(id, "王晨", 23);
        System.out.println(user.toString());
        userService.addUser(user);
    }

    @GetMapping("/user1")
    public void test(){
        List<String> list = Arrays.asList(
                "用户5",
                "用户4",
                "用户3",
                "用户1",
                "用户2"
        );
        for (int i = 0; i < list.size(); i++) {
            redisZSet.addZset("My_Zset", list.get(i), i);
        }

    }
    @GetMapping("/addScore/{value}")
    public void addScore(@PathVariable("value") String value){
        redisZSet.addZsetScore("My_Zset", value, 1);
    }

    @GetMapping("/rank")
    public void rank(){
        Set<Object> rank = redisZSet.reverseRank("My_Zset", 0, 10);
        System.out.println("rank = " + rank.toString());
    }

    @GetMapping("/test")
    public void test1(){
       //redisZSet.testGeo();
        redisBit.myTestBit();
    }

    @GetMapping("/find/{key}/{value}")
    public void testBit(@PathVariable String key, @PathVariable Long value){
        System.out.println("sign = " + redisBit.mySigned(key, value));
    }

    @GetMapping("/bitField/{field}")
    public void testBitField(@PathVariable String field){
        redisBit.bitField(field);
    }


    @GetMapping(value = "/test_topic")
    public void testTopic(){

        for (int i = 0; i < 20  ; i++) {
            redisTemplate.convertAndSend("myTopic", "我来了redis消息！！ " + i);
        }


    }


    public void test04(){

        List<Object> objects = redisTemplate.executePipelined((RedisCallback<String>) redisConnection -> {
            return null;
        });
    }

}
