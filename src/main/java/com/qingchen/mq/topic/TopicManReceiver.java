package com.qingchen.mq.topic;

import com.qingchen.mq.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName TopicManReceiver
 * @description:
 * @author: WangChen
 * @create: 2020-05-27 17:29
 **/

@Component
@RabbitListener(queues = RabbitMqConfig.QUEUE_NAME2)
public class TopicManReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}
