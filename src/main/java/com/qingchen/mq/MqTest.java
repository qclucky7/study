package com.qingchen.mq;

import com.qingchen.mq.config.death.DeathQueue;
import com.qingchen.mq.config.RabbitMqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName MqTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-28 14:32
 **/
@RestController
@RequestMapping("/mq")
public class MqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send1")
    public void sendMessage() {

        Map<String, String> map = new HashMap<>();
        map.put("队列1第一条消息", "我来了");

        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, map);
    }

    @GetMapping("/send2")
    public void sendMessage2() {

        Map<String, String> map = new HashMap<>();
        map.put("队列2第一条消息", "我来了");

        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_ROOT_KEY, map);
    }


    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }


    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);

        return "ok";

    }

    @GetMapping("/death")
    public String deathMessage() {


        String messageData = "deadletter";
        //发送消息的时候设置消息过期时间
        rabbitTemplate.convertAndSend(DeathQueue.BUSINESS_EXCHANGE_NAME, null, messageData,
                message -> {
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                }

        );

        //rabbitTemplate.convertAndSend(DeathQueue.BUSINESS_EXCHANGE_NAME, null, messageData);

        return "ok";

    }



}
