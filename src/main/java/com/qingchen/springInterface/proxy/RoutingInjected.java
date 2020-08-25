package com.qingchen.springInterface.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName InitializeOrder
 * @Author WangChen
 * @Date 2020/3/30 11:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Component
public @interface RoutingInjected {

}
