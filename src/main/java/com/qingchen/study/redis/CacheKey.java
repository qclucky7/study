package com.qingchen.study.redis;

/**
 * 缓存key
 * com.vlife.timerbox.cache.CacheKey
 * @author XuShenglai  <br/>
 * create at 2018年3月26日 下午2:32:57
 */
public enum CacheKey {

	/**
	 * 视频生成状态，用于防止统一id重复生成
	 */
	generate_video_status,
	/**
	 * 百度token
	 */
	baidu_token,

	component_authorize;
}
