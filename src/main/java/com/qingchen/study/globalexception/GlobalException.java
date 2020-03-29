package com.qingchen.study.globalexception;

import com.qingchen.study.vlife.ErrorCodeException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    public ErrorResponse globalExceptionHandle(Exception exception){

        ErrorResponse errorResponse = new ErrorResponse();
        if (exception instanceof ErrorCodeException){
            ErrorCodeException errorCodeException = (ErrorCodeException) exception;
            errorResponse.setMsg(errorCodeException.getCode().getType().name());
            errorResponse.setCode(errorCodeException.getCode().getStatus());
        } else if (exception instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
            StringBuilder stringBuilder = new StringBuilder();
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            bindingResult.getAllErrors().forEach(e -> stringBuilder.append(e.getDefaultMessage()).append(", "));
            System.out.println(stringBuilder.toString());
            errorResponse.setMsg(stringBuilder.toString());
            errorResponse.setCode(500);
        } else {
             errorResponse.setMsg(exception.getMessage());
             errorResponse.setCode(500);
        }

        return errorResponse;

    }


}
