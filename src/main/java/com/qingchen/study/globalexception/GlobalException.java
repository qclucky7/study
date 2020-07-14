package com.qingchen.study.globalexception;

import com.qingchen.study.vlife.ErrorCodeException;
import com.qingchen.study.vlife.ErrorType;
import com.qingchen.study.vlife.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName GlobalException
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 15:12
 **/
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<?> globalExceptionHandle(Exception exception){

        if (exception instanceof ErrorCodeException){
            ErrorCodeException errorCodeException = (ErrorCodeException) exception;
            return Result.ofFail(errorCodeException.getCode().getStatus(), errorCodeException.getCode().name());
        } else if (exception instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
            StringBuilder stringBuilder = new StringBuilder();
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                if (i == bindingResult.getAllErrors().size() - 1) {
                    stringBuilder.append(bindingResult.getAllErrors().get(i).getDefaultMessage());
                } else {
                    stringBuilder.append(bindingResult.getAllErrors().get(i).getDefaultMessage()).append(" ,");
                }
            }

            return Result.ofFail(HttpStatus.error_no_data.getStatus(), stringBuilder.toString());
        } else if (exception instanceof com.qingchen.study.utils.mybatis.GlobalException){

            return Result.ofFail(HttpStatus.error_no_data.getStatus(), exception.getMessage());
        }
        exception.printStackTrace();


        return Result.ofFail(HttpStatus.error_unknown.getStatus(), ErrorType.server_error.name());

    }


}
