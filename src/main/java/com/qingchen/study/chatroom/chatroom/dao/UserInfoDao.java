package com.qingchen.study.chatroom.chatroom.dao;

import com.qingchen.study.chatroom.chatroom.model.po.UserInfo;

public interface UserInfoDao {

    
    UserInfo getByUsername(String username);
    
    UserInfo getByUserId(String userId);
}
