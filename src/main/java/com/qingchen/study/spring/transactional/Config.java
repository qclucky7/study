package com.qingchen.study.spring.transactional;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName Conifg
 * @description:
 * @author: WangChen
 * @create: 2020-04-05 18:57
 **/

//springboot默认是开始这个注解的
@EnableTransactionManagement
@Transactional(propagation = Propagation.NEVER)
public class Config {

}
