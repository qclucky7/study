package com.qingchen.study.maptoobj;

import java.lang.annotation.*;

/**
 * @author WangChen
 * @since 2020-11-30 10:46
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface NameInMap {
    String value();

    String[] alternate() default {};
}
