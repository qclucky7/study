package com.qingchen.study.ratelimit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @InterfaceName RateLimit
 * @description:
 * @author: WangChen
 * @create: 2020-06-18 18:35
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RateLimit {

    /**
     * 可通过请求数
     * @return
     */
    int accessible() default 10;

    /**
     * 时间区间
     * @return
     */
    int timeInterval() default 1;

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
