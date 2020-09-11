package com.qingchen.study.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RedisConfig
 * @description:
 * @author: WangChen
 * @create: 2020-04-16 15:46
 **/
@Configuration
@EnableCaching
public class RedisConfig{

    private StringRedisSerializer stringRedisSerializer =  new StringRedisSerializer();

    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            stringBuilder.append("[");
            for (Object obj : objects) {
                stringBuilder.append(obj.toString());
            }
            stringBuilder.append("]");

            return stringBuilder.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认策略，未配置的 key 会使用这个
                this.getRedisCacheConfigurationWithTtl(ChronoUnit.HOURS.getDuration().getSeconds(), redisSerializer()),
                // 指定 key 策略
                this.getRedisCacheConfigurationMap()
        );
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(CacheProvider.values().length);
        for (CacheProvider cacheEntity : CacheProvider.values()) {
            redisCacheConfigurationMap.put(cacheEntity.name(),
                    this.getRedisCacheConfigurationWithTtl(cacheEntity.getExpire(), redisSerializer()));
        }
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Long seconds, RedisSerializer<?> redisSerializer) {

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration.serializeKeysWith(RedisSerializationContext
                .SerializationPair
                .fromSerializer(stringRedisSerializer));

        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(redisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory, RedisSerializer<?> redisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // key的序列化类型
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //value的序列化方式.
        redisTemplate.setValueSerializer(redisSerializer);

        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(redisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer(){

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //反序列化时候遇到不匹配的属性并不抛出异常
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //序列化时候遇到空对象不抛出异常
        //objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化的时候如果是无效子类型,不抛出异常
        //objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        //不使用默认的dateTime进行序列化,
        //objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        //使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
        //objectMapper.registerModule(new JavaTimeModule());

        //启用反序列化所需的类型信息,在属性中添加@class
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        return jackson2JsonRedisSerializer;
    }


    /**
     * redis发布订阅配置
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter() {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MyRedisChannelListener());
        messageListenerAdapter.setSerializer(redisSerializer());
        return messageListenerAdapter;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("myTopic"));

        return container;
    }



}
