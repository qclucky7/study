package com.qingchen.demo.mydemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * @ClassName Message
 * @description:
 * @author: WangChen
 * @create: 2020-04-26 12:49
 **/
@TableName(value = "t_message")
public class Message implements Cloneable{

    @TableId(value = "message_id", type = IdType.AUTO)
    private long id;

    private String url;

    private String context;

    private LocalDateTime localDateTime;

    private static Message message = new Message();

    public Message(long id, String url, String context, LocalDateTime localDateTime) {
        this.id = id;
        this.url = url;
        this.context = context;
        this.localDateTime = localDateTime;
    }

    public Message(){
        super();
    }

    public static Message getMessage(){
        try {
            return (Message)message.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return message;
    }

    public long getId() {
        return id;
    }

    public Message setId(long id) {

        this.id = id;
        return this;
    }

    public String getContext() {
        return context;
    }

    public Message setContext(String context) {

        this.context = context;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Message setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Message setLocalDateTime(LocalDateTime localDateTime) {

        this.localDateTime = localDateTime;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", context='" + context + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
