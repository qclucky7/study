package com.qingchen.study.globalexception;


import org.springframework.http.HttpStatus;

/**
 * @ClassName ErrorResponse
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 15:18
 **/
public class Result<R> {

    private boolean success;
    private int code;
    private String msg;
    private R data;

    public static <R> Result<R> ofSuccess(R data) {
        return new Result<R>()
                .setSuccess(true)
                .setMsg("success")
                .setData(data);
    }

    public static <R> Result<R> ofSuccessMsg(String msg) {
        return new Result<R>()
                .setSuccess(true)
                .setMsg(msg);
    }

    public static <R> Result<R> ofFail(int code, String msg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <R> Result<R> ofSuccess(HttpStatus httpStatus) {
        return new Result<R>()
                .setCode(httpStatus.value())
                .setSuccess(true)
                .setMsg(httpStatus.getReasonPhrase());
    }

    public static <R> Result<R> ofFail(HttpStatus httpStatus) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(httpStatus.value());
        result.setMsg(httpStatus.getReasonPhrase());
        return result;
    }

    public static <R> Result<R> ofThrowable(int code, Throwable throwable) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<R> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<R> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R getData() {
        return data;
    }

    public Result<R> setData(R data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

