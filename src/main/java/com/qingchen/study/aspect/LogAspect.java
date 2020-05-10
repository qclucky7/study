package com.qingchen.study.aspect;

import com.alibaba.fastjson.JSON;
import com.qingchen.demo.mydemo.model.Message;
import com.qingchen.study.queue.ActiveQueue;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @ClassName LogAspect
 * @description:
 * @author: WangChen
 * @create: 2020-04-26 17:13
 **/
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

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
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n-----------------------request header start-------------------------------\n");
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            String header = request.getHeader(headName);
            stringBuilder.append(headName).append(":").append(header).append("\n");
        }
        stringBuilder.append("-----------------------request header end-------------------------------");

        log.info(stringBuilder.toString());

        String stringBuilderRequestBody =
                "\n<-------------------------RequestBody------------------------------>\n" +
                "request method" + ":" + method + "\n" +
                "request protocol" + ":" + request.getRequestURI() + "\n" +
                "request arg" + ":" + JSON.toJSONString(Arrays.toString(joinPoint.getArgs())) + "\n" +
                "<-------------------------RequestBody------------------------------>";
        log.info(stringBuilderRequestBody);


        Message message = Message.getMessage();
        message.setUrl(request.getRequestURI());
        message.setContext(JSON.toJSONString(Arrays.toString(joinPoint.getArgs())));
        message.setLocalDateTime(LocalDateTime.now());

        System.out.println(message);

        ActiveQueue.getInstance().pushMessage(message);

        Object proceed = joinPoint.proceed();

        if (ObjectUtils.isNotEmpty(proceed)){
            log.info("<-------------------------ResponseBody------------------------------>");
            log.info("response = {}", proceed.toString());
            log.info("<-------------------------ResponseBody------------------------------>");
        }

        return proceed;
    }

}
