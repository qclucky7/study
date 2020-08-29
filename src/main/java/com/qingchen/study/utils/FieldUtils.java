package com.qingchen.study.utils;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @ClassName FieldUtils
 * @description:
 * @author: WangChen
 * @create: 2020-08-25 12:06
 **/
public class FieldUtils {

    public FieldUtils() {
    }

    public static Field getField(Class<?> clazz, String fieldName) throws IllegalStateException {
        Assert.notNull(clazz, "Class required");
        Assert.hasText(fieldName, "Field name required");

        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException var3) {
            if (clazz.getSuperclass() != null) {
                return getField(clazz.getSuperclass(), fieldName);
            } else {
                throw new IllegalStateException("Could not locate field '" + fieldName + "' on class " + clazz);
            }
        }
    }

    public static Object getFieldValue(Object bean, String fieldName) throws IllegalAccessException {
        Assert.notNull(bean, "Bean cannot be null");
        Assert.hasText(fieldName, "Field name required");
        String[] nestedFields = StringUtils.tokenizeToStringArray(fieldName, ".");
        Class<?> componentClass = bean.getClass();
        Object value = bean;
        String[] var5 = nestedFields;
        int var6 = nestedFields.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String nestedField = var5[var7];
            Field field = getField(componentClass, nestedField);
            field.setAccessible(true);
            value = field.get(value);
            if (value != null) {
                componentClass = value.getClass();
            }
        }

        return value;
    }

    public static Object getProtectedFieldValue(String protectedField, Object object) {
        Field field = getField(object.getClass(), protectedField);

        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception var4) {
            ReflectionUtils.handleReflectionException(var4);
            return null;
        }
    }

    public static void setProtectedFieldValue(String protectedField, Object object, Object newValue) {
        Field field = getField(object.getClass(), protectedField);

        try {
            field.setAccessible(true);
            field.set(object, newValue);
        } catch (Exception var5) {
            ReflectionUtils.handleReflectionException(var5);
        }

    }
}
