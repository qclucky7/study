package com.qingchen.study.ratelimit;

import com.qingchen.study.globalexception.Result;
import com.qingchen.study.utils.other.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @ClassName AbstractRateLimitSupport
 * @description:
 * @author: WangChen
 * @create: 2020-06-19 12:14
 **/
public abstract class AbstractRateLimitSupport {

    private static Map<String, WindowQpsController> windowQpsControlMap = new ConcurrentHashMap<>(64);

    private static Map<Method, String> methodMappingMap = new ConcurrentHashMap<>(64);
    

    protected Object filterResources(Method method, ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {

        String mappingUrl = methodMappingMap.computeIfAbsent(method, value -> handleMappingUrl(method));


        WindowQpsController windowQpsController = handleRateLimitAnnotation(mappingUrl, rateLimit);

        if (windowQpsController.isPass()){
            return joinPoint.proceed();
        }

        return Result.ofFail(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    private WindowQpsController handleRateLimitAnnotation(String mappingUrl, RateLimit rateLimit){

        return windowQpsControlMap.computeIfAbsent(mappingUrl, windowQpsController ->
                new WindowQpsController(rateLimit.accessible(), rateLimit.timeInterval(), rateLimit.timeUnit()));

    }

    
    private String handleMappingUrl(Method method){
        
        String classMapping = resolveClassAnnotation(method);
        String methodMapping = resolveMethodAnnotation(method);
        if (StringUtil.isNotEmpty(classMapping)){
            return classMapping + methodMapping;
        }
        return methodMapping;
        
    }

    private String resolveClassAnnotation(Method method){
        
        RequestMapping requestMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
        if (requestMapping == null){
            return null;
        }
        return requestMapping.value()[0];
        
    }

    private String resolveMethodAnnotation(Method method){
        
        Annotation[] annotations = method.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof RequestMapping){
                return ((RequestMapping) annotation).value()[0];
            }
            if (annotation instanceof GetMapping){
                return ((GetMapping) annotation).value()[0];
            }
            if (annotation instanceof PostMapping){
                return ((PostMapping) annotation).value()[0];
            }
            if (annotation instanceof PutMapping){
                return ((PutMapping) annotation).value()[0];
            }
            if (annotation instanceof DeleteMapping){
                return ((DeleteMapping) annotation).value()[0];
            }
        }

        return "@" + method.getName();
    }

    protected boolean checkRateLimitAnnotation(RateLimit rateLimit){

        if (rateLimit.accessible() <= 0 || rateLimit.timeInterval() <= 0){
            return false;
        }
        return true;
    }

    protected Map<String, WindowQpsController> getStatistics(){
         return new HashMap<>(windowQpsControlMap);
    }
}
