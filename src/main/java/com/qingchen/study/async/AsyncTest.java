package com.qingchen.study.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsyncTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 11:42
 **/
@RestController
public class AsyncTest {

    @Autowired
    private AsyncService service;

    @GetMapping("/async")
    public void myTest(){
        service.testAsync();
    }


    @GetMapping("/pool")
    public void myTest1(){

        ThreadPoolExecutor produce = ThreadPoolManager.produce(ThreadPoolType.ASIN_TASK);

        produce.execute(()-> System.out.println("1111111111111111111111111"));


        System.out.println(ThreadPoolManager.threadPoolCacheMap.toString());
    }
}
