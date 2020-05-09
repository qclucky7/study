package com.qingchen.study.decoration.strategy;

/**
 * @InterfaceName EncryptionStrategy
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 15:26
 **/
public interface EncryptionStrategy {

    String encrypt(String data);

    String decrypt(String data);
}
