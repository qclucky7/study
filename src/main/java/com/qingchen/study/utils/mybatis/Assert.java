package com.qingchen.study.utils.mybatis;


import com.qingchen.study.utils.other.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName Assert
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 09:20
 **/
public final class Assert {
    private Assert() {
    }

    public static void isTrue(boolean expression, String message, Object... params) {
        if (!expression) {
            throw ExceptionUtils.throwEx(message, params);
        }
    }

    public static void isFalse(boolean expression, String message, Object... params) {
        isTrue(!expression, message, params);
    }

    public static void isNull(Object object, String message, Object... params) {
        isTrue(ObjectUtils.isNull(object), message, params);
    }

    public static void notNull(Object object, String message, Object... params) {
        isTrue(ObjectUtils.isNotNull(object), message, params);
    }

    public static void notEmpty(String value, String message, Object... params) {
        isTrue(StringUtils.isNotBlank(value), message, params);
    }

    public static void notEmpty(Collection<?> collection, String message, Object... params) {
        isTrue(CollectionUtils.isNotEmpty(collection), message, params);
    }

    public static void notEmpty(Map<?, ?> map, String message, Object... params) {
        isTrue(CollectionUtils.isNotEmpty(map), message, params);
    }

    public static void notEmpty(Object[] array, String message, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), message, params);
    }
}
