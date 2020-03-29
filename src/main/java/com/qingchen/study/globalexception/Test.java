package com.qingchen.study.globalexception;

import com.qingchen.study.vlife.ErrorCode;
import com.qingchen.study.vlife.ErrorCodeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @ClassName test
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 15:28
 **/
@RestController
public class Test {

    @PostMapping("/test")
    public void test(){
        throw new ErrorCodeException(ErrorCode.no_privilege);
    }

    @PostMapping("/error")
    public String test1(@RequestBody @Valid ErrorResponse errorResponse ){

        System.out.println(errorResponse.toString());

        return errorResponse.getMsg();
    }




}
