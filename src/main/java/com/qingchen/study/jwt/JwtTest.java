package com.qingchen.study.jwt;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Base64;

/**
 * @ClassName JwtTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-03 14:31
 **/
public class JwtTest {

    /**
     * jwt组成的部分
     * head 头
     * Payload 载荷
     * secret 签名
     */
    @Test
    public void myTest(){

        String token = JWTUtils.getInstance().getToken(123456);
        System.out.println(token);

        String[] split = "eyJhbGciOiJTSEEyNTYiLCJ0eXAiOiJqd3QifQ==.eyJjcmVhdGVUaW1lIjoxNTg4NDk1NzkyNzM3LCJleHBpcmUiOjE1ODg0OTU4MDI3MzcsInVzZXJJZCI6MTIzNDU2fQ==.535b58fb9c93b1e21b1d888d1ac9e0544229ad1bc999f7131292e800e21df58d".split("\\.");

        String tokenHeader = split[0];
        String tokenBody = split[1];
        String tokenSecret = split[2];

        System.out.println("header = " + tokenHeader);
        System.out.println("body = " + tokenBody);
        System.out.println("secret = " + tokenSecret);


        if (JWTUtils.getInstance().generateSecret(tokenHeader, tokenBody).equals(tokenSecret)){
            System.out.println("没有篡改！！");
        }

        String s = new String(Base64.getUrlDecoder().decode(tokenBody.getBytes()));
        System.out.println(s);

        String replace = new String(Base64.getUrlDecoder().decode(tokenHeader.getBytes()));
        System.out.println(replace);

        JWTUtils.TokenBody tokenBody1 = JSON.parseObject(
                s,
                JWTUtils.TokenBody.class);
        JWTUtils.TokenHeader tokenHeader1 = JSON.parseObject(
                replace,
                JWTUtils.TokenHeader.class);
        System.out.println("tokenHeader = " + tokenHeader1.toString());
        System.out.println("tokenBody = " + tokenBody1.toString());
        if (tokenBody1.getExpire() < System.currentTimeMillis()){
            System.out.println("过期了。。。");
        }


    }




}
