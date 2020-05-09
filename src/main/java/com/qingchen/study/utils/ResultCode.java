package com.qingchen.study.utils;


/**
 * 返回的错误code
 *
 */
public enum ResultCode {
	/**
	 * 成功
	 */
	success,
	/**
	 * 填写问卷 && 参与成功
	 */
	remarks_success_join_success,
	/**
	 * 失败
	 */
	fail,
	/**
	 * 填写问卷失败  && 参与成功
	 */
	remarks_fail_join_success,
	/**
	 * 数据不合法
	 */
	data_invalid,
	/**
	 * 时间数据无效
	 */
	time_invalid,
	/**
	 * 地点数据无效
	 */
	location_invalid,
	/**
	 * 手机号格式不对
	 */
	mobile_not_right,
	
	/**
	 * 手机号已经存在
	 */
	mobile_exist,
	
	/**
	 * 手机号不存在
	 */
	mobile_not_exist,
	
	/**
	 * 密码格式不对
	 */
	password_not_right,
	
	/**
	 * 出生日期格式或日期不对
	 */
	birthday_not_right,
	
	/**
	 * 验证码已过期
	 */
	code_expired,

	/**
	 * 验证码不对
	 */
	code_not_right,
	
	/**
	 * 密码错误
	 */
	password_error,
	
	/**
	 * 新旧密码不一致
	 */
	password_not_same,
	
	/**
	 * 不支持的协议
	 */
	not_support,
	
	/**
	 * 用户不存在
	 */
	
	user_not_found,
	
	/**
	 * 用户关系不存在
	 */
	
	user_relation_not_found,
	
	/**
	 * 请求参数为空
	 */
	parameter_null_or_empty,
	
	/**
	 * 对象不存在
	 */
	object_not_found,
	
	/**
	 * 不是发起者
	 */
	not_sponsor,
	/**
	 * 用户名已存在
	 */
	user_exist,
	/**
	 * 用户名不存在
	 */
	user_not_exist,
	/**
	 * 账号被封
	 */
	user_is_banned,
	
	/**
	 * 用户未注册
	 */
	no_register,
	
	/**
	 * 用户已注册
	 */
	registered,

	/**
	 * 客户端版本不支持
	 */
	version_not_support,
	
	/**
	 * 拒绝登录
	 */
	login_denied,
	
	/**
	 * 三方账号绑定失败
	 */
	bind_failed,
	
	/**
	 * 第三方账号未绑定
	 */
	third_not_bound,
	
	
	/**
	 * 已投过
	 */
	voted,
	/**
	 * 超过截止时间
	 */
	timeup,
	/**
	 * 未到开始时间
	 */
	notime,
	/**
	 * 人数达上限
	 */
	actor_full,
	
	/**
	 * 需要进行手机号码认证才能完成注册
	 */
	require_authentication,
	
	/**
	 * 无法获得微信的unionid
	 */
	no_unionid,
	
	/**
	 * 拒绝注册
	 */
	regist_denied,
	
	/**
	 * 重复的操作
	 */
	repeated_submit,
	
	/**
	 * 角色已经存在
	 */
	role_exists,
	
	/**
	 * 相同权限角色已存在
	 */
	same_privileges_exists,
	
	/**
	 * 角色不存在
	 */
	role_not_exist,
	
	/**
	 * 已经为用户添加过角色;
	 */
	user_already_has_role,
	/**
	 * 权限不足
	 */
	no_privilege,
	/**
	 * 已经存在
	 */
	already_exist,
	/**
	 * 已经被注册
	 */
	already_register,
	/**
	 * 用户没有这个角色
	 */
	user_no_role,
	/**
	 * 用户允许加入的组织达到上限
	 */
	tag_full,
	/**
	 * 一键催促时间限制
	 */
	urge_time_not_allow,
	/**
	 * 发起者不允许退出 
	 */
	sponsor_no_quit,
	/**
	 * 用户信息不完整
	 */
	user_info_no_integrity, 
	/**
	 * 数据结构不完整
	 */
	data_no_integrity, 
	/**
	 * 审核状态已改变
	 */
	review_status_changed, 
	/**
	 * 需要完善用户信息
	 */
	require_completing,
	/**
	 * 需要用户授权以获得昵称和头像
	 */
	require_authorize,
	/**
	 * 审核中
	 */
	reviewing,
	/**
	 * 不在审核中
	 */
	not_reviewing,
	/**
	 * 网络请求失败
	 */
	request_fail,
	
	/**
	 * 未知错误
	 */
	unkown_error,
	/**
	 * 用户已申请
	 */
	apply_already,
	/**
	 * 已经退出评选
	 */
	quited_selection,
	/**
	 * 有子栏目
	 */
	have_child_channel,
	/**
	 * 拥抱次数限制
	 */
	hug_num_limit,
	/**
	 * 已经拥抱过该用户
	 */
	already_hug,
	/**
	 * 微信session过期
	 */
	wechat_session_expired,
	// 仅组织发起人
	only_tag_sponsor,
	// 不在线
	not_online,

	rejected,
	to_max_limit,

	wechat_not_found,
	appid_tester_limit,
	wechat_test_limit,

	already_authorize,
	;
}