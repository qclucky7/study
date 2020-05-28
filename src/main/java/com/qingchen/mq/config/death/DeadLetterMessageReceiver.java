package com.qingchen.mq.config.death;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.qingchen.mq.config.death.DeathQueue.DEAD_LETTER_QUEUEA_NAME;
import static com.qingchen.mq.config.death.DeathQueue.DEAD_LETTER_QUEUEB_NAME;

/**
 * @ClassName DeadLetterMessageReceiver
 * @description:
 * @author: WangChen
 * @create: 2020-05-28 16:28
 **/
@Component
public class DeadLetterMessageReceiver {

    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息A：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
