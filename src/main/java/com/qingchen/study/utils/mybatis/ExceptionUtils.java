package com.qingchen.study.utils.mybatis;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @ClassName ExceptionUtils
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 09:31
 **/
public class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static GlobalException throwEx(String msg, Throwable t, Object... params) {
        return new GlobalException(StringUtils.format(msg, params), t);
    }

    public static GlobalException throwEx(String msg, Object... params) {
        return new GlobalException(StringUtils.format(msg, params));
    }

    public static GlobalException throwEx(Throwable t) {
        return new GlobalException(t);
    }
}
