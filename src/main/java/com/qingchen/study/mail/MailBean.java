package com.qingchen.study.mail;

import java.util.List;

/**
 * @ClassName MailBean
 * @description:
 * @author: WangChen
 * @create: 2020-03-13 17:45
 **/
public class MailBean {

    /**
     * 发送地址
     */
    private List<String> sendMailAddress;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String text;

    /**
     * 附件
     */
    private List<String> filePath;

    /**
     * 附件名称
     */
    private List<String> attachmentFilename;

    /**
     * 内容ID，用于发送静态资源邮件时使用
     */
    private String contentId;


    public MailBean() {
    }

    public MailBean(List<String> sendMailAddress, String subject, String text, List<String> filePath, List<String> attachmentFilename, String contentId) {
        this.sendMailAddress = sendMailAddress;
        this.subject = subject;
        this.text = text;
        this.filePath = filePath;
        this.attachmentFilename = attachmentFilename;
        this.contentId = contentId;
    }

    public List<String> getSendMailAddress() {
        return sendMailAddress;
    }

    public void setSendMailAddress(List<String> sendMailAddress) {
        this.sendMailAddress = sendMailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getFilePath() {
        return filePath;
    }

    public void setFilePath(List<String> filePath) {
        this.filePath = filePath;
    }

    public List<String> getAttachmentFilename() {
        return attachmentFilename;
    }

    public void setAttachmentFilename(List<String> attachmentFilename) {
        this.attachmentFilename = attachmentFilename;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
