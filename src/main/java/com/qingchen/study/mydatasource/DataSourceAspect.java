package com.qingchen.study.mydatasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName DataSourceAspect
 * @description:
 * @author: WangChen
 * @create: 2020-05-04 16:36
 **/
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Pointcut("@annotation(com.qingchen.study.mydatasource.DataSourceSelector)")
    public void pointCut(){

    };

//    @Before("pointCut()")
//    public void handleDataSource(JoinPoint joinPoint) {
//
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Class<?> clazz = joinPoint.getThis().getClass();
//        System.out.println(clazz.toString());
//        //DataSourceSelector annotation = clazz.getAnnotation(DataSourceSelector.class);
//        DataSourceSelector annotation = signature.getMethod().getAnnotation(DataSourceSelector.class);
//        System.out.println(annotation.dataSource().name());
//        System.out.println(annotation.toString());
//        if (annotation != null){
//            DynamicDataSource.setDataBaseType(annotation.dataSource());
//        }
//
//    }
}
