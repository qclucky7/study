package com.qingchen.sdk;

import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @ClassName SdkConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-27 13:22
 **/
@Configuration
@PropertySource(value = "classpath:application.properties")
public class SdkConfig {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    @Value("${oss.endpoint}")
    private String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
    // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucket}")
    private String bucket;

    @PostConstruct
    public void initialize(){
        AliyunOSSUtils.setConfig(this);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getBucket() {
        return bucket;
    }
}
