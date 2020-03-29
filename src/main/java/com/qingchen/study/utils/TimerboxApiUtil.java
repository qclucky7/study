package com.qingchen.study.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerboxApiUtil {

	private static Logger log = LoggerFactory.getLogger(TimerboxApiUtil.class);

	public static JSONObject doPostJson(String url, JSONObject dataJson, Header[] headers) throws Exception {
		log.info("doPostJson url = {}, requestData = {}", url, dataJson);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", StringUtils.randomString(20));
		jsonObject.put("time", System.currentTimeMillis());
		jsonObject.put("data", dataJson);
		byte[] data = jsonObject.toJSONString().getBytes();
		String result = HttpClientUtils.doPostString(url, new ByteArrayEntity(data), headers);
		JSONObject resJson = JSONObject.parseObject(result);
		if (resJson == null) {
			throw new RuntimeException("request url " + url + " get no response");
		}
		log.info("doPostJson result = {}", resJson);
		return resJson.getJSONObject("data");
	}

}
