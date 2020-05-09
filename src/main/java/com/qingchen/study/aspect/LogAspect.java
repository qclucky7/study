package com.qingchen.study.aspect;

import com.alibaba.fastjson.JSON;
import com.qingchen.demo.mydemo.model.Message;
import com.qingchen.study.queue.ActiveQueue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @ClassName LogAspect
 * @description:
 * @author: WangChen
 * @create: 2020-04-26 17:13
 **/
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.qingchen.*.*.controller.*Controller.*(..))")
    public void pointCut(){
    }


    @Around("pointCut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        System.out.println("method = "+ method);
        System.out.println("ip = "+ remoteAddr);
        System.out.println("url = " + request.getRequestURI());
        System.out.println("arg = " + JSON.toJSONString(Arrays.toString(joinPoint.getArgs())));
        System.out.println("method = "+ signature.getDeclaringTypeName() + "." + signature.getMethod().getName());

        Message message = Message.getMessage();
        message.setUrl(request.getRequestURI());
        message.setContext(JSON.toJSONString(Arrays.toString(joinPoint.getArgs())));
        message.setLocalDateTime(LocalDateTime.now());

        System.out.println(message);

        ActiveQueue.getInstance().pushMessage(message);

        Object proceed = joinPoint.proceed();

        System.out.println("返回值 = " + JSON.toJSONString(proceed));

        return proceed;
    }

}
