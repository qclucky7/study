package com.qingchen.tdd;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean register(@RequestBody UserDTO userDTO){

        return true;
    }


    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){

        return true;
    }
}
