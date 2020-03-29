package com.qingchen.study.globalexception;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @ClassName ErrorResponse
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 15:18
 **/
public class ErrorResponse {

    @NotNull(message = "msg不能为空")
    private String msg;
    @NotNull(message = "code不能为0")
    @Min(value = 1 ,message = "不能为0")
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
