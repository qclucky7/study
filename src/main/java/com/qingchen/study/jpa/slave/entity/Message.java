package com.qingchen.study.jpa.slave.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName Message
 * @description:
 * @author: WangChen
 * @create: 2020-05-17 16:32
 **/
@Entity
@Table(name = "t_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "message_id")
    private long messageId;

    @Column(nullable = false, name = "url")
    private String url;

    @Column(nullable = false, name = "context")
    private String context;

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;


    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", url='" + url + '\'' +
                ", context='" + context + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
