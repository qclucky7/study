package com.qingchen.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfig
 * @description:
 * @author: WangChen
 * @create: 2020-05-27 16:39
 **/
@Configuration
public class RabbitMqConfig {

    public final static String ROUTING_ROOT_KEY = "routing.#";

    public final static String ROUTING_KEY = "routing.01";

    public final static String QUEUE_NAME = "topic.first";

    public final static String QUEUE_NAME2 = "topic.two";

    public final static String EXCHANGE_NAME = "exchange";


    // 创建队列
    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME);
    }

    // 创建队列
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME2);
    }

    // 创建一个 topic 类型的交换器
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue1()).to(exchange()).with(ROUTING_KEY);
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(exchange()).with( ROUTING_ROOT_KEY);
    }
}
