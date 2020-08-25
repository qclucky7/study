package com.qingchen.study.decoration.strategy;

/**
 * @InterfaceName EncryptionStrategy
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 15:26
 **/
public interface EncryptionStrategy {

    /**
     * 加密
     * @param data
     * @return
     */
    String encrypt(String data);

    /**
     * 解密
     * @param data
     * @return
     */
    String decrypt(String data);
}
