package com.qingchen.study.chatroom.chatroom.service.impl;

import com.qingchen.study.chatroom.chatroom.dao.UserInfoDao;
import com.qingchen.study.chatroom.chatroom.model.po.UserInfo;
import com.qingchen.study.chatroom.chatroom.model.vo.ResponseJson;
import com.qingchen.study.chatroom.chatroom.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    
    @Override
    public ResponseJson getByUserId(String userId) {
        UserInfo userInfo = userInfoDao.getByUserId(userId);
        return new ResponseJson().success()
                .setData("userInfo", userInfo);
    }

}
