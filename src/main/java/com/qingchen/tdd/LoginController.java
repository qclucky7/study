package com.qingchen.tdd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingchen.study.globalexception.Result;
import com.qingchen.study.utils.mybatis.GlobalException;
import com.qingchen.study.vlife.ErrorCode;
import com.qingchen.study.vlife.ErrorCodeException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URL;
import java.util.List;

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

    @PostMapping("/message")
    public void myTest(@RequestBody @NotBlank(message = "列表id为空") String ids){

        System.out.println(ids);

        JSONObject jsonObject = JSON.parseObject(ids);
        List<Long> list = JSON.parseArray(jsonObject.getString("ids"), Long.TYPE);

        System.out.println(list.toString());

        throw new GlobalException(MultiLanguageUtils.getMessage("message.ex.test"));

    }

}
