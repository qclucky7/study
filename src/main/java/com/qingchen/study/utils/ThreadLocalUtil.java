package com.qingchen.study.utils;

/**
 * @ClassName ThreadLocalUtil
 * @Description
 * @Author xushenglai
 * @Date 2018/8/8 下午5:29
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<String> requestIdLocal = new ThreadLocal();

    public static void setRequestId(String requestId) {
        requestIdLocal.set(requestId);
    }

    public static String getRequestId() {
        return (String)requestIdLocal.get();
    }

    public static void removeRequestId() {
        requestIdLocal.remove();
    }

}
