package com.qingchen.study.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 公共缓存
 * com.vlife.timerbox.cache.CommonCache
 * @author XuShenglai  <br/>
 * create at 2018年3月26日 下午2:35:50
 */
public class CommonSimpleCache {
	
	@Autowired
	private CacheManager cacheManager;
	
	public void putString(CacheKey cacheKey, String key, String value) {
		cacheManager.getCache("common_simple_cache").put(cacheKey.name() + "-" + key, value);
	}
	
	public String getString(CacheKey cacheKey, String key) {
		Cache.ValueWrapper valueWrapper = cacheManager.getCache("common_simple_cache").get(cacheKey.name() + "-" + key);
		if (valueWrapper == null) {
			return null;
		}
		return valueWrapper.get().toString();
	}
	
	public void removeString(CacheKey cacheKey, String key) {
		cacheManager.getCache("common_simple_cache").evict(cacheKey.name() + "-" + key);
	}
	
	public void putObj(CacheKey cacheKey, String key, Object obj) {
		cacheManager.getCache("common_simple_cache").put(cacheKey.name() + "-" + key, obj);
	}
	
	public Object getObj(CacheKey cacheKey, String key) {
		Cache.ValueWrapper valueWrapper = cacheManager.getCache("common_simple_cache").get(cacheKey.name() + "-" + key);
		if (valueWrapper == null) {
			return null;
		}
		return valueWrapper.get();
	}
	
	public void removeObj(CacheKey cacheKey, String key) {
		cacheManager.getCache("common_simple_cache").evict(cacheKey.name() + "-" + key);
	}

}
