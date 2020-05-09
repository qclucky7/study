package com.qingchen.study.vlife;

import java.sql.SQLException;

/**
 * 用户Session的数据操作类
 * @author xuliang
 *
 */
public interface ISessionDao {
	
	/**
	 * 查询session
	 * @return
	 */
	UserSession query(String sid);
	
	/**
	 * 更新session
	 * @param session
	 * @return
	 */
	boolean save(UserSession session);
	
	/**
	 * 删除session
	 * @param session
	 * @return
	 */
	boolean delete(UserSession session);
	
	/**
	 * 使session活跃
	 * @param session
	 * @return
	 */
	boolean activity(UserSession session);
	
	/**
	 * 根据用户id删除
	 * @param userId
	 * @return
	 * Create by XuShenglai at 2017年7月21日 下午12:12:14
	 * @throws SQLException 
	 */
	boolean deleteByUserId(long userId, String appId) throws SQLException;
	
	/**
	 * 根据userid获取session。为web后门而做。
	 * @param userId
	 * @return
	 * Create by XuShenglai at 2017年8月10日 下午4:52:17
	 * @throws SQLException 
	 */
	UserSession queryByUserId(long userId, String appId) throws SQLException;
}
