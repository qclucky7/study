package com.qingchen.study.utils.mybatis;

import com.qingchen.study.decoration.strategy.EncryptionStrategy;
import com.qingchen.study.vlife.AESUtil;

import java.util.Base64;

public enum EncryptUtils implements EncryptionStrategy{


    /**
     * AES加密解密策略
     */
    AES{
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
    },

    /**
     * BASE64加密解密策略
     */
    BASE64{

        @Override
        public String encrypt(String data) {

            return Base64.getEncoder().encodeToString(data.getBytes());
        }

        @Override
        public String decrypt(String data) {

            return new String(Base64.getDecoder().decode(data));
        }

        public String urlEncrypt(String data){

            return Base64.getUrlEncoder().encodeToString(data.getBytes());
        }

        public String urlDecrypt(String data){

            return new String(Base64.getUrlDecoder().decode(data));
        }
    }
}
