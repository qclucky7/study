package com.qingchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableRabbit
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(value = "com.qingchen.demo.*.mapper.*.*")
@ServletComponentScan
//@EnableElasticsearchRepositories(value = "com.qingchen.study.elasticsearch")
public class StudyApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudyApplication.class, args);
    }

}
