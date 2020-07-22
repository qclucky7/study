package com.qingchen.study.chatroom.chatroom.dao;


import com.qingchen.study.chatroom.chatroom.model.po.GroupInfo;

public interface GroupInfoDao {

    
    GroupInfo getByGroupId(String groupId);
}
