package com.qingchen.study.redis;

import com.qingchen.study.vlife.ISessionDao;
import com.qingchen.study.vlife.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;


/**
 * session redis缓存
 *
 * @author YanWenXiong
 * create at 2018年1月5日 下午3:58:06
 */
public class SessionRedisDao implements ISessionDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CacheManager cacheManager;

    @Override
    public UserSession query(String sid) {
        logger.info("[SessionRedisDao] query sessionId={}", sid);
        Cache.ValueWrapper wrapper = cacheManager.getCache("session").get(sid);
        Object object = wrapper == null ? null : wrapper.get();
        if (object != null) {
            String json = object.toString();
            logger.info("[SessionRedisDao] query json={}", json);
            UserSession session = new UserSession();
            try {
                session = session.parseFromJson(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return session;
        }
        return null;

    }

    @Override
    public boolean save(UserSession session) {
        logger.info("[SessionRedisDao] save session={}", session.formatJson());
        cacheManager.getCache("session").put(session.getSid(), session.formatJson());
        return true;
    }

    @Override
    public boolean delete(UserSession session) {
        logger.info("[SessionRedisDao] delete session={}", session.getSid());
        cacheManager.getCache("session").evict(session.getSid());
        return true;
    }

    @Override
    public boolean activity(UserSession session) {
        //logger.info("[SessionRedisDao] save session={}", session);
        //cacheManager.getCache("session").put(session.getSid(), new RedisValueWrapper(session.formatJson()));
        return true;
    }

    @Override
    public boolean deleteByUserId(long userId, String appId) throws SQLException {
        return false;
    }

    @Override
    public UserSession queryByUserId(long userId, String appId) throws SQLException {
        return null;
    }

}