package com.qingchen.study.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName MyRestTemplate
 * @description:
 * @author: WangChen
 * @create: 2020-05-26 14:55
 **/
public class MyRestTemplate {

    @Autowired
    private RestTemplate restTemplate;


    public void myTest(){

        final String filePath = "E:";
        final String fileName = "testFile.txt";
        final String url = "http://localhost:8080/file/upload";

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath+"/"+fileName);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("filename",fileName);

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);

        String s = restTemplate.postForObject(url, files, String.class);
        System.out.println(s);

    }
}
