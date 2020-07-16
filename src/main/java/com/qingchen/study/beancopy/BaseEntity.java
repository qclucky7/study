package com.qingchen.study.beancopy;

import java.io.Serializable;

/**
 * @ClassName BaseEntity
 * @description:
 * @author: WangChen
 * @create: 2020-06-26 19:52
 **/
public abstract class BaseEntity implements Serializable {

    private long id;
    private long createTime;
    private long updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
