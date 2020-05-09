package com.qingchen.study.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @ClassName SwaggerConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-29 13:01
 **/
@Configuration
//新版不加这个注解会出现拦截！
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket initSwagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swagger-api")
                .apiInfo(this.initApiInfo())
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qingchen.demo.mydemo.controller"))
                .build();
    }

    private ApiInfo initApiInfo(){
        return new ApiInfo(
                "项目标题",
                "项目描述",
                "1.0",
                "服务条款",
                 new Contact("王晨","",""),
                "",
                "",
                Collections.emptyList()
                );
    }
}
