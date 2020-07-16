package com.qingchen.study.async;

import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolManager
 * @description:
 * @author: WangChen
 * @create: 2020-07-13 11:51
 **/
public class ThreadPoolManager {

    /**
     * 线程池缓存
     */
    public static Map<ThreadPoolType, ThreadPoolExecutor> threadPoolCacheMap = new ConcurrentHashMap<>();

    public static ThreadPoolExecutor produce(ThreadPoolType poolType){
//        ThreadPoolExecutor threadPoolExecutor = threadPoolCacheMap.get(poolType);
//        if (threadPoolExecutor == null){
//            ThreadPoolExecutor poolExecutor =  new ThreadPoolExecutor(32, 128,
//                    10L, TimeUnit.SECONDS,
//                    new LinkedBlockingQueue<Runnable>(),
//                    new ThreadNameFactory(poolType.name()));
//            threadPoolCacheMap.put(poolType, poolExecutor);
//            return poolExecutor;
//        }
//        return threadPoolExecutor;

        return Optional.ofNullable(threadPoolCacheMap.get(poolType)).orElseGet(() -> {
            ThreadPoolExecutor poolExecutor =  new ThreadPoolExecutor(32, 128,
                    10L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    new ThreadNameFactory(poolType.name()));
            threadPoolCacheMap.put(poolType, poolExecutor);
            return poolExecutor;
        });
    }


    public static void main(String[] args) {
        ThreadPoolExecutor produce = ThreadPoolManager.produce(ThreadPoolType.ASIN_TASK);

        produce.execute(() -> System.out.println("11111"));

        System.out.println(threadPoolCacheMap.toString());
    }




}
