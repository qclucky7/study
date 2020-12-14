package com.qingchen.study.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @ClassName MyRedisChannelListener
 * @description:
 * @author: WangChen
 * @create: 2020-09-11 17:21
 **/
public class MyRedisChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {

        String channel = new String(message.getChannel());
        String body = new String(message.getBody());

        System.out.println(channel);
        System.out.println(body);
        System.out.println(new String(bytes));
    }

}
