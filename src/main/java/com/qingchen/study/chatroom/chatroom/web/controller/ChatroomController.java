package com.qingchen.study.chatroom.chatroom.web.controller;


import com.qingchen.study.chatroom.chatroom.model.vo.ResponseJson;
import com.qingchen.study.chatroom.chatroom.service.UserInfoService;
import com.qingchen.study.chatroom.chatroom.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chatroom")
public class ChatroomController {

    @Autowired
    UserInfoService userInfoService;
    
    /**
     * 描述：登录成功后，调用此接口进行页面跳转
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toChatroom() {
        return "chatroom";
    }
    
    /**
     * 描述：登录成功跳转页面后，调用此接口获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/get_userinfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJson getUserInfo(@PathVariable("id") String userId) {
        //Object userId = session.getAttribute(Constant.USER_TOKEN);
        return userInfoService.getByUserId(userId);
    }
}
