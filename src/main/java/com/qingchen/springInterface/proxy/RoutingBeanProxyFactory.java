package com.qingchen.springInterface.proxy;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName RoutingBeanProxyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 11:19
 **/
public class RoutingBeanProxyFactory {

    private static boolean sign = true;

    public static Object createProxy(Class<?> type, Map<String, ?> candidates) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(type, candidates));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {

        private Object targetObject;

        public VersionRoutingMethodInterceptor(Class<?> clazz, Map<String, ?> beans) {

            String uncapitalize = StringUtils.uncapitalize(clazz.getSimpleName());

            System.out.println("uncapitalize = " + uncapitalize);

            System.out.println("VersionRoutingMethodInterceptor beans = " + beans.toString());

            Object o = beans.get("operateImplV1");
            Object o1 = beans.get("operateImplV2");

            System.out.println("o = " + o);
            System.out.println("o1 = " + o1);

            if (sign){
                this.targetObject = o1;
            } else {
                this.targetObject = o;
            }
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println(invocation.getMethod());
            System.out.println(Arrays.toString(invocation.getArguments()));
            return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
        }

    }
}
