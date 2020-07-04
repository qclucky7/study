package com.qingchen.study.mail;

import com.qingchen.study.utils.mybatis.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName mailUtil
 * @description:
 * @author: WangChen
 * @create: 2020-03-13 16:46
 **/
public class MailUtil {

    private static Session session;

    static {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        System.setProperty("mail.mime.splitlongparameters", "false");  // linux 会默认为 true，会截断附件名
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session sessionInit = Session.getDefaultInstance(props);
        sessionInit.setDebug(true);
        session = sessionInit;
    }

    public static void sendComplexEmail(String fromEmailName, List<String> toEmailAccounts, String mailTitle, String content, List<String> filePaths) throws Exception{
        sendComplexEmail(fromEmailName, toEmailAccounts, mailTitle,content, filePaths, null);
    }

    public static void sendComplexEmail(String fromEmailName, List<String> toEmailAccounts, String title, String content, List<String> filePaths, List<String> fileNames) throws Exception {


        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, "271724646@qq.com", fromEmailName, toEmailAccounts, title, content, filePaths, fileNames);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        transport.connect("271724646@qq.com", "titydjomdecqbjgh");

        // 6. 发送邮件, 发到所有的收件地址
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    private static MimeMessage createMimeMessage(Session session, String fromEmail, String fromEmailName, List<String> toEmails, String title, String content, List<String> filePaths, List<String> fileNames) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(fromEmail, fromEmailName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        for (String toMail : toEmails) {
            int pos = toMail.indexOf('@');
            String toEmailName = "";
            if (pos != -1) {
                toEmailName = toMail.substring(0, pos);
            }
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail, toEmailName, "UTF-8"));
        }

        // 4. Subject: 邮件主题
        message.setSubject(title, "UTF-8");

        // 5. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content == null ? "" : content, "text/html;charset=UTF-8");

        // 6. 进行节点组装
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.setSubType("mixed");

        // 7. 创建附件“节点”
        if (CollectionUtils.isNotEmpty(filePaths)) {
            for (int i = 0; i < filePaths.size(); i++) {
                String filePath = filePaths.get(i);
                MimeBodyPart attachment = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource(filePath));
                attachment.setDataHandler(dh);
                if (CollectionUtils.isNotEmpty(fileNames) && fileNames.size() == filePaths.size()) {
                    attachment.setFileName(MimeUtility.encodeText(fileNames.get(i)));
                } else {
                    attachment.setFileName(MimeUtility.encodeText(dh.getName()));
                }
                mm.addBodyPart(attachment);
            }
        }
        // 8. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        // 9. 设置发件时间
        message.setSentDate(new Date());

        // 10. 保存上面的所有设置
        message.saveChanges();

        return message;
    }

    public static void main(String[] args) throws Exception{
        List<String> list1 = Arrays.asList("D:\\Huawei Share\\OneHop\\王晨.pdf", "D:\\Huawei Share\\OneHop\\2.mp4");
        List<String> list2 = Arrays.asList("这是pdf.pdf", "这是MP4.mp4");
        List<String> list = Arrays.asList("271724646@qq.com");
        MailUtil.sendComplexEmail("test账号", list, "test标题", "test内容", list1, list2);
    }
}
