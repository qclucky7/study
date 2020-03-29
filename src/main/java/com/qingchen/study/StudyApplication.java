package com.qingchen.study;

import com.qingchen.study.observer.ActivitySubjectCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootApplication
public class StudyApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudyApplication.class, args);

    }

}
