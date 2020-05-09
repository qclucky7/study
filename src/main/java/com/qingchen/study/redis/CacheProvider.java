package com.qingchen.study.redis;

/**
 * 缓存实体
 * @author wangchen
 */
public enum CacheProvider {


    /**用户信息过期时间**/
    UserInfoList(DurationConstant.ONE_DAY),

    UserInfoListAnother(DurationConstant.ONE_DAY);

    private long expire;

    CacheProvider(long expire) {
        this.expire = expire;
    }

    public long getExpire() {
        return expire;
    }




}
