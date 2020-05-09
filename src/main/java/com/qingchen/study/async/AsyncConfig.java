package com.qingchen.study.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;

import java.util.concurrent.*;

/**
 * @ClassName AsyncConfig
 * @description:
 * @author: WangChen
 * @create: 2020-05-06 11:22
 **/
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    ThreadPoolExecutor poolExecutor =  new ThreadPoolExecutor(2, 3,
                                                              10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
        new ThreadNameFactory("async"));

    @Override
    public Executor getAsyncExecutor() {
        return poolExecutor;
    }
}
