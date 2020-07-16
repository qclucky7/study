package com.qingchen.springInterface.factoryBean;

import org.springframework.beans.factory.FactoryBean;

import java.security.MessageDigest;

/**
 * @ClassName MessageDigestFactoryBean
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 14:04
 **/
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest> {

    private String algorithmName;

    public MessageDigestFactoryBean(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public MessageDigest getObject() throws Exception {
        return MessageDigest.getInstance(algorithmName);
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
