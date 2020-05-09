package com.qingchen.study.globalexception;

import com.qingchen.study.vlife.ErrorCode;
import com.qingchen.study.vlife.ErrorCodeException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @ClassName test
 * @description:
 * @author: WangChen
 * @create: 2020-02-27 15:28
 **/
@RestController
@RequestMapping("/my")
public class Test {

    @GetMapping("/test")
    public void test(){
        System.out.println("收到消息！！");
    }

    @PostMapping("/error")
    public String test1(@RequestBody @Valid ErrorResponse errorResponse ){

        System.out.println(errorResponse.toString());

        return errorResponse.getMsg();
    }




}
