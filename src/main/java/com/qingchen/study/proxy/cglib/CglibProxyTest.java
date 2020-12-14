package com.qingchen.study.proxy.cglib;

import com.qingchen.tdd.UserDTO;
import org.junit.Test;

import java.util.IdentityHashMap;

/**
 * @ClassName CglibProxyTest
 * @description:
 * @author: WangChen
 * @create: 2020-10-19 14:18
 **/
public class CglibProxyTest {


    @Test
    public void test(){

        IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();

        Target proxyInstance = CglibProxyFactory.getProxyInstance(Target.class, new CglibMethodInterceptor());

        proxyInstance.doSomething();

    }


    @Test
    public void test1(){

        UserDTO userDTO = new UserDTO();
        UserDTO userDTO1 = new UserDTO();

        System.out.println(userDTO.hashCode() == userDTO1.hashCode());
        System.out.println(userDTO.equals(userDTO1));

    }


    @Test
    public void test2(){

        Integer integer = 32;
        Integer integer1 = 32;
        System.out.println(integer == integer1);
    }


    @Test
    public void test3(){

        String a = "abc";
        String b = "abc";

        System.out.println(a == b);
    }


}

