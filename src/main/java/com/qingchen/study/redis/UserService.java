package com.qingchen.study.redis;

import com.qingchen.study.spring.myconfig.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import static com.qingchen.study.redis.CacheNameProvider.getName;
import static com.qingchen.study.redis.CacheProvider.UserInfoList;

/**
 * @ClassName UserService
 * @description:
 * @author: WangChen
 * @create: 2020-04-16 16:38
 **/
@Service
public class UserService {

    @Cacheable(value = "UserInfoList", key = "'UserInfo_' + #user.id")
    public User addUser(User user){

        return user;
    }

    @CachePut(value = "UserInfoList", key = "'UserInfo_' + #user.id")
    public User updateUser(User user){

        return user;
    }

    @CacheEvict(value = "UserInfoList", key = "'UserInfo_' + #user.id")
    public User deleteUser(User user){

        return user;
    }

}
