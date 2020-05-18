package com.qingchen.study.jpa;

import com.qingchen.study.jpa.master.CustomRepository;
import com.qingchen.study.jpa.master.entity.Custom;
import com.qingchen.study.jpa.slave.MessageRepository;
import com.qingchen.study.jpa.slave.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @ClassName JPATest
 * @description:
 * @author: WangChen
 * @create: 2020-05-17 15:05
 **/
@RestController
public class JPATest {

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private MessageRepository messageRepository;


    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/jpa/add")
    public void myTest() {
        Custom custom = new Custom();
        custom.setCustAddress("地址");
        custom.setCustLevel("101010101010");
        custom.setCustName("海底捞");
        custom.setCustPhone("1234567890");
        custom.setCustSource("海底捞！");
        custom.setCustIndustry("服务");
        customRepository.save(custom);

        Message message = new Message();
        message.setUrl("/baidu/xxx");
        message.setContext("从库内容归滚了吗？？？/");
        message.setCreateTime(LocalDateTime.now());

        messageRepository.save(message);

        int i = 3/0;
    }
}
