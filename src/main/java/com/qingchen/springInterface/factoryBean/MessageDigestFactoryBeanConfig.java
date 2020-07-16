package com.qingchen.springInterface.factoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MessageDigestFactoryBeanConfig
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 14:09
 **/
@Configuration
public class MessageDigestFactoryBeanConfig {

    @Bean
    public MessageDigestFactoryBean md5(){
        return new MessageDigestFactoryBean("MD5");
    }

    @Bean
    public MessageDigestFactoryBean sha1(){
        return new MessageDigestFactoryBean("SHA-1");
    }
}
