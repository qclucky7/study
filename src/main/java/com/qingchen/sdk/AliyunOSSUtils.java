package com.qingchen.sdk;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.ByteArrayInputStream;

/**
 * @ClassName AliyunOSSUtils
 * @description:
 * @author: WangChen
 * @create: 2020-04-27 12:33
 **/
public class AliyunOSSUtils {


    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
    // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucket;


    public static void setConfig(SdkConfig sdkConfig){
        endpoint = sdkConfig.getEndpoint();
        accessKeyId = sdkConfig.getAccessKeyId();
        accessKeySecret = sdkConfig.getAccessKeySecret();
        bucket = sdkConfig.getBucket();
    }

    private static class OssClientProvider{
        private static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    private static OSS getInstance(){
        return OssClientProvider.ossClient;
    }


    public static void upload(byte[] bytes, String fileName, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        getInstance().putObject(bucket, fileName, new ByteArrayInputStream(bytes), objectMetadata);
    }


}
