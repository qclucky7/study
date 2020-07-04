package com.qingchen.study.utils.mybatis;

/**
 * @ClassName ArrayUtils
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 09:40
 **/
public class ArrayUtils {

    private ArrayUtils() {
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }
}
