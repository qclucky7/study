package com.qingchen.study.chatroom.chatroom.service;

import com.qingchen.study.chatroom.chatroom.model.vo.ResponseJson;

public interface UserInfoService {

    ResponseJson getByUserId(String userId);
}
