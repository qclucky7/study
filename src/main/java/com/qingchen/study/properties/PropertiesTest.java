package com.qingchen.study.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PropertiesTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-27 15:24
 **/
@Configuration
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:application.properties")
public class PropertiesTest {

    /**
     * 两种方式
     * 1.@PropertySource配合 @value , 缺点map, list这种的参数不好配置
     * 2.@PropertySource @ConfigurationProperties 可以配置map, list等复杂类型的值
     */

    private Map<String, String> map = new HashMap<>();

    private List<Integer> list = new ArrayList<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
