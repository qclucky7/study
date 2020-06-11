package com.qingchen.mq.exchang;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName FanoutReceiverA
 * @description:
 * @author: WangChen
 * @create: 2020-05-27 18:21
 **/
@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverD {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverD消费者收到消息  : " +testMessage.toString());
    }

}

