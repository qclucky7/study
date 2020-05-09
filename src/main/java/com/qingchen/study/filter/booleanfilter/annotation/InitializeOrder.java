package com.qingchen.study.filter.booleanfilter.annotation;

import java.lang.annotation.*;

/**
 * @ClassName InitializeOrder
 * @Author WangChen
 * @Date 2020/3/30 11:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface InitializeOrder {

    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    int value() default LOWEST_PRECEDENCE;

    boolean effective() default true;
}
