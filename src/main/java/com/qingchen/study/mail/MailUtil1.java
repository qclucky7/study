package com.qingchen.study.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName MailUtil1
 * @description:
 * @author: WangChen
 * @create: 2020-03-13 17:43
 **/

public class MailUtil1 {

    private Logger logger = LoggerFactory.getLogger(MailUtil1.class);

    ExecutorService mailPoll = Executors.newFixedThreadPool(3);

    private static final Pattern PATTERN =
            Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMailAddress;


    public void sendMail() {
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom(fromMailAddress);
        mimeMessage.setTo(fromMailAddress);
        mimeMessage.setSubject("SpringBoot集成JavaMail实现邮件发送");
        mimeMessage.setText("SpringBoot集成JavaMail实现邮件发送正文");
        //mailSender.send(mimeMessage);
    }


    /**
     * 发送邮件-附件邮件
     * @param mailBean
     */
    public void sendMailAttachment(MailBean mailBean) throws Exception{
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromMailAddress);
            System.out.println("fromMailAddress" + fromMailAddress);
            List<String> sendMailAddress = mailBean.getSendMailAddress();
            if (isEmail(sendMailAddress)){
                helper.setTo(sendMailAddress.toArray(new String[0]));
            } else {
                logger.error("mail格式错误");
                return;
            }

            helper.setSubject(mailBean.getSubject());
            helper.setText(mailBean.getText(), true);
            List<String> filePath = mailBean.getFilePath();
            List<String> filenames = mailBean.getAttachmentFilename();
            for (int i = 0; i < filePath.size(); i++) {
                FileSystemResource fileSystemResource = new FileSystemResource(filePath.get(i));
                // 增加附件名称和附件
                if (filenames.size() == filePath.size() && filenames.get(i) != null){
                    helper.addAttachment(filenames.get(i), fileSystemResource.getFile());
                } else {
                    helper.addAttachment(MimeUtility.encodeWord(Optional.ofNullable(fileSystemResource.getFilename()).orElse(" ")),
                            fileSystemResource.getFile());
                }

            }
            System.out.println("哪里慢");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 邮箱校验
     *
     * @param mailList
     * @return
     */
    public static boolean isEmail(List<String> mailList) {
        for (String mail : mailList) {
            if (!PATTERN.matcher(mail).matches()){
                return false;
            }
        }
        return true;
    }

    public static void test(String... parts){

        String[] var2 = parts;
        int var3 = parts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String bp = var2[var4];
            System.out.println(bp);
        }

    }

    public static void main(String[] args) {

       /* List<String> sendMailAddress = new ArrayList<>();
        sendMailAddress.add("1");
        sendMailAddress.add("2");
        //String[] strings = sendMailAddress.toArray(new String[sendMailAddress.size()]);
        String[] strings = sendMailAddress.toArray(new String[0]);
        System.out.println(Arrays.toString(strings));*/

        List<MailBean> mailBeans = Arrays.asList(
                new MailBean(null, "普通", "内容1", null, null, null),
                new MailBean(null, "普通", "内容2", null, null, null),
                new MailBean(null, "普通", "内容3", null, null, null),
                new MailBean(null, "普通", "内容4", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "高级", "内容", null, null, null),
                new MailBean(null, "特殊", "内容", null, null, null),
                new MailBean(null, "特殊", "内容", null, null, null),
                new MailBean(null, "特殊", "内容", null, null, null),
                new MailBean(null, "特殊", "内容", null, null, null)
        );

        //test(new String[]{"1","2","3"});

        Map<String, List<String>> collect = mailBeans.stream()
                .collect(Collectors.groupingBy(MailBean::getSubject, Collectors.mapping(MailBean::getText, Collectors.toList())));

        Map<String, Optional<MailBean>> collect1 = mailBeans.stream()
                .collect(Collectors.groupingBy(MailBean::getSubject,
                        Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(MailBean::getText)))));

        Map<String, MailBean> collect2 = mailBeans.stream().collect(Collectors.toMap(MailBean::getSubject,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(MailBean::getText)),
                TreeMap::new
                )
        );

        int i = Collections.binarySearch(mailBeans, new MailBean(null, "普通", "内容4", null, null, null),
                Comparator.comparing(MailBean::getText));

        System.out.println(i);
        System.out.println(collect.toString());
        System.out.println(collect1.toString());

    }

}
