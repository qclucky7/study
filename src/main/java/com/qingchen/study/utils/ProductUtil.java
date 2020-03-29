package com.qingchen.study.utils;

/**
 * product工具
 * com.vlife.framework.server.http.utils.ProductUtil
 * @author XuShenglai  <br/>
 * create at 2018年3月22日 下午4:31:15
 */
public class ProductUtil {

	private static final ThreadLocal<Long> productIdLocal = new ThreadLocal<>();
	private static final ThreadLocal<String> productKeyLocal = new ThreadLocal<>();
	private static final ThreadLocal<String> appIdLocal = new ThreadLocal<>();

	public static void setProductId(long productId) {
		productIdLocal.set(productId);
	}
	
	public static long getProductId() {
		Long result = productIdLocal.get();
		if (result != null) {
			return  result;
		} else {
			return 0L;
		}
	}
	
	public static void removeProductId() {
		productIdLocal.remove();
	}

	public static void setProductKey(String productKey) {
		productKeyLocal.set(productKey);
	}

	public static String getProductKey() {
		return productKeyLocal.get();
	}

	public static void removeProductKey() {
		productKeyLocal.remove();
	}
	
	public static void setAppId(String appId) {
		appIdLocal.set(appId);
	}
	
	public static String getAppId() {
		return appIdLocal.get();
	}
	
	public static void removeAppId() {
		appIdLocal.remove();
	}
	
}
