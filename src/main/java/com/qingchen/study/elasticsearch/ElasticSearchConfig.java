package com.qingchen.study.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName ElasticSearchConfig
 * @description:
 * @author: WangChen
 * @create: 2020-05-16 17:04
 **/
@Configuration
public class ElasticSearchConfig {

    //@Bean("transportClient")
    public TransportClient setTransportClient(){
        try {
        Settings settings = Settings.builder()
                .put("cluster", "my-application")
                .build();
        return new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 8080));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }


}
