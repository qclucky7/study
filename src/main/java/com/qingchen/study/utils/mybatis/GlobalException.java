package com.qingchen.study.utils.mybatis;

/**
 * @ClassName GlobalException
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 09:33
 **/
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(Throwable throwable) {
        super(throwable);
    }

    public GlobalException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
