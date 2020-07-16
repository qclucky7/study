package com.qingchen.springInterface;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName CommandLineRunnerTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 13:37
 **/
@Component
public class CommandLineRunnerTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunnerTest初始化");
    }
}
