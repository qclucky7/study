package com.qingchen.demo.mydemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.qingchen.demo.mydemo.mapper.plus.PlusMapperTest;
import com.qingchen.demo.mydemo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;
import static com.baomidou.mybatisplus.core.toolkit.Wrappers.query;

/**
 * @ClassName MyService
 * @description:
 * @author: WangChen
 * @create: 2020-05-20 21:56
 **/
@Service
public class MyServiceImp{

    //@Autowired
    //private PlusMapperTest plusMapperTest;


//    public void myPlus(){
//
//        QueryChainWrapper<Message> between = plusMapperTest.query().eq("id", 1)
//                .between("create_time", 1, 2);
//
//        List<Message> list = plusMapperTest.list(between);
//
//
//    }
}
