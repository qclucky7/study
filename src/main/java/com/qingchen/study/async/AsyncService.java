package com.qingchen.study.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName AsyncService
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 11:43
 **/
@Service
public class AsyncService {

    @Async
    public void testAsync(){
        System.out.println(Thread.currentThread().getName());
    }
}
