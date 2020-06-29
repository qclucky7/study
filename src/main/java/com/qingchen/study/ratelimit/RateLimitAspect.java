package com.qingchen.study.ratelimit;

import com.qingchen.study.globalexception.Result;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RateLimitAspect
 * @description:
 * @author: WangChen
 * @create: 2020-06-18 18:39
 **/
@Aspect
@Component
public class RateLimitAspect extends AbstractRateLimitSupport{

    @Around("@annotation(com.qingchen.study.ratelimit.RateLimit)")
    public Object resolveLimit(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        if (!checkRateLimitAnnotation(rateLimit)){
            return Result.ofFail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "RateLimit注解错误的参数");
        }

        return filterResources(method, joinPoint, rateLimit);

    }









}
