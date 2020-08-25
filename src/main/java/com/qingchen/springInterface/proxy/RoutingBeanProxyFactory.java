package com.qingchen.springInterface.proxy;


import com.qingchen.springInterface.proxy.test.OperateTest;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RoutingBeanProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 11:19
 **/
public class RoutingBeanProxyFactory {

    public static Object createProxy(Class<?> type, Map<String, ?> candidates) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(type, candidates));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {

        private Map<String, Object> cacheMap = new ConcurrentHashMap<>(2);

        public VersionRoutingMethodInterceptor(Class<?> clazz, Map<String, ?> beans) {

            String uncapitalize = StringUtils.uncapitalize(clazz.getSimpleName());

            System.out.println("uncapitalize = " + uncapitalize);

            System.out.println("VersionRoutingMethodInterceptor beans = " + beans.toString());

            beans.keySet().forEach(className -> {
                              if (className.toLowerCase().contains("v1")){
                                  cacheMap.put("1.0", beans.get(className));
                              } else {
                                  cacheMap.put("2.0", beans.get(className));
                              }
                          });

        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println(invocation.getMethod());
            System.out.println(Arrays.toString(invocation.getArguments()));
            if ("1.0".equals(OperateTest.versions.get())){
                return invocation.getMethod().invoke(cacheMap.get("1.0"), invocation.getArguments());
            }
            return invocation.getMethod().invoke(cacheMap.get("2.0"), invocation.getArguments());
        }

    }
}
