package com.qingchen.study.chatroom.chatroom.service;

import com.qingchen.study.chatroom.chatroom.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;

public interface SecurityService {

    ResponseJson login(String username, String password, HttpSession session);
    
    ResponseJson logout(HttpSession session);
}
