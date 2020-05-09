package com.qingchen.study.decoration.strategy;

import com.qingchen.study.vlife.AESUtil;

/**
 * @ClassName AESStrategy
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 15:27
 **/
public class AESStrategy implements EncryptionStrategy{

    @Override
    public String encrypt(String data){
        try {
            return AESUtil.encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String data){
        try {
            return AESUtil.decrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
