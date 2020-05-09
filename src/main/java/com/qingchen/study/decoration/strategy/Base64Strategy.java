package com.qingchen.study.decoration.strategy;

import java.util.Base64;

/**
 * @ClassName Base64Strategy
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 16:23
 **/
public class Base64Strategy implements EncryptionStrategy{

    @Override
    public String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    @Override
    public String decrypt(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}
