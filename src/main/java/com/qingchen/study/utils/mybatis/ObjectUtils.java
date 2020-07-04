package com.qingchen.study.utils.mybatis;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName ObjectUtils
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 09:40
 **/
public class ObjectUtils {

    private ObjectUtils() {
    }

    public static boolean isNull(Object... objs) {
        Object[] var1 = objs;
        int var2 = objs.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object obj = var1[var3];
            if (isEmpty(obj)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotNull(Object... obj) {
        return !isNull(obj);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else {
            return obj instanceof Map && ((Map) obj).isEmpty();
        }
    }
}
