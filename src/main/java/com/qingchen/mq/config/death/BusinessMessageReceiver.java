package com.qingchen.mq.config.death;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.qingchen.mq.config.death.DeathQueue.BUSINESS_QUEUEA_NAME;
import static com.qingchen.mq.config.death.DeathQueue.BUSINESS_QUEUEB_NAME;

/**
 * @ClassName BusinessMessageReceiver
 * @description:
 * @author: WangChen
 * @create: 2020-05-28 16:37
 **/
@Component
public class BusinessMessageReceiver {

    @RabbitListener(queues = BUSINESS_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println(msg);
        boolean ack = true;
        try {
            if (msg.contains("deadletter")){
                throw new RuntimeException("dead letter exception");
            }
        } catch (Exception e){
            ack = false;
        }
        if (!ack){
            System.out.println("异常");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = BUSINESS_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到业务消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
