package com.qingchen.study.vlife;

/**
 * 错误类型
 * @author xuliang
 *
 */
public enum ErrorType {
	/**
	 * Session 失效
	 */
	session_expires,
	
	/**
	 * 稍后再发送
	 */
	later,
	
	/**
	 * 服务器错误
	 */
	server_error,
	
	/**
	 * 请求的数据不存在
	 */
	not_found,
	
	/**
	 * 没有权限
	 */
	no_privilege,
	/**
	 * 未认证
	 */
	unauthorized,
	/**
	 * 认证信息审核中
	 */
	reviewing,
	/**
	 * 老板开除了秘书
	 */
	fired,
	/**
	 * 认证信息被拒绝
	 */
	rejected,

	/**
	 * 文件不存在
	 */
	file_is_not_exist;
}
