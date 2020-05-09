package com.qingchen.study.vlife;

import com.qingchen.study.utils.DigestStringUtils;
import com.qingchen.study.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName SessionService
 * @Description
 * @Author xushenglai
 * @Date 2018/8/21 下午3:04
 */
public class SessionService {

    private AtomicLong id = new AtomicLong(0x01);
    private final int length = 10;
    private String header = DigestStringUtils.hash(StringUtils.randomString(length)+System.currentTimeMillis());

    @Resource
    private ISessionDao sessionRedisDao;
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 创建一个Session
     * @return
     */
    public UserSession create() {
        String sid = createUniqueId();
        UserSession session = new UserSession();
        session.setSid(sid);
        session.setCreate_time(System.currentTimeMillis());
        return session;
    }

    /**
     * 创建唯一值
     * @return
     */
    private String createUniqueId() {
        String value = Long.toHexString(id.getAndIncrement());
        StringBuilder buf = new StringBuilder();
        if (value.length() < length) {
            buf.append(header.substring(0, length - value.length()));
        }
        buf.append(value);
        //距离 2016年06月12日00时00分00秒000毫秒 相差的秒数，因为这个算法是这天设计的
        buf.append(Long.toHexString((System.currentTimeMillis() - 1465660800000l) / 1000));
        return buf.toString();
    }

    /**
     * 保存session
     * @param session
     * @return
     * @throws SQLException
     */
    public boolean save(UserSession session) throws SQLException {
        return sessionRedisDao.save(session);
    }

    public UserSession query(String sessionId) throws SQLException {
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        }
        UserSession session = sessionRedisDao.query(sessionId);
        if (session == null) {
            log.info("query session is null");
            return null;
        }
        if (System.currentTimeMillis() > session.getExpires_time()) {
            log.info("expires_time={}", session.getExpires_time());
            //Session失效了
            sessionRedisDao.delete(session);
            return null;
        }
        return session;
    }

    public long getExpires() {
        return 2 * 24 * 60 * 60 * 1000l;
    }

}
