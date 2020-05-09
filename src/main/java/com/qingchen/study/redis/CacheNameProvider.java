package com.qingchen.study.redis;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CacheNameProvider
 * @description:
 * @author: WangChen
 * @create: 2020-04-19 21:09
 **/
public class CacheNameProvider {

        private static volatile Map<String, Long> map;

        static {
            CacheProvider[] values = CacheProvider.values();
            map = new HashMap<>(values.length);
            for (CacheProvider value : values) {
                map.put(value.name(), value.getExpire());
            }
        }

        public static Long getName(CacheProvider cacheProvider){
            return map.get("");
        }

}
