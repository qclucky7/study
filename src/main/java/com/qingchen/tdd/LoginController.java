package com.qingchen.tdd;

import com.qingchen.study.globalexception.Result;
import com.qingchen.study.utils.mybatis.GlobalException;
import com.qingchen.study.vlife.ErrorCode;
import com.qingchen.study.vlife.ErrorCodeException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @description:
 * @author: WangChen
 * @create: 2020-06-14 15:13
 **/
@RestController
@RequestMapping("/api/v1")
public class LoginController {


    @PostMapping("/register")
    public Result<UserDTO> register(@RequestBody @Validated UserDTO userDTO){

        return Result.ofSuccess(userDTO);
    }

    @PostMapping("/test")
    public void test(@RequestParam("test") String test){
        System.out.println(test);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){

        return true;
    }

    @GetMapping("/message")
    public void myTest(){

        throw new GlobalException(MultiLanguageUtils.getMessage("message.ex.test"));

    }

}
