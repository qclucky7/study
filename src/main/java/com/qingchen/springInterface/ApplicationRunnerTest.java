package com.qingchen.springInterface;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationRunnerTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 13:36
 **/
@Component
public class ApplicationRunnerTest implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunnerTest初始化");
    }
}
