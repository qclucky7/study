package com.qingchen.study.utils.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ClassUtils
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 09:23
 **/
public final class ClassUtils {

    private static final char PACKAGE_SEPARATOR = '.';
    private static final List<String> PROXY_CLASS_NAMES = Arrays.asList("net.sf.cglib.proxy.Factory", "org.springframework.cglib.proxy.Factory", "javassist.util.proxy.ProxyObject", "org.apache.ibatis.javassist.util.proxy.ProxyObject");

    private ClassUtils() {
    }

    public static boolean isBoolean(Class<?> type) {
        return type == Boolean.TYPE || Boolean.class == type;
    }

    public static boolean isProxy(Class<?> clazz) {
        if (clazz != null) {
            Class[] var1 = clazz.getInterfaces();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Class<?> cls = var1[var3];
                if (PROXY_CLASS_NAMES.contains(cls.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException var2) {
            throw ExceptionUtils.mpe("实例化对象时出现错误,请尝试给 %s 添加无参的构造方法", var2, new Object[]{clazz.getName()});
        }
    }

    public static Class<?> toClassConfident(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException var2) {
            throw ExceptionUtils.mpe("找不到指定的class！请仅在明确确定会有 class 的时候，调用该方法", var2, new Object[0]);
        }
    }

    public static String getPackageName(Class<?> clazz) {
        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(clazz, "Class must not be null", new Object[0]);
        return getPackageName(clazz.getName());
    }

    public static String getPackageName(String fqClassName) {
        Assert.notNull(fqClassName, "Class name must not be null", new Object[0]);
        int lastDotIndex = fqClassName.lastIndexOf(46);
        return lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "";
    }
}
