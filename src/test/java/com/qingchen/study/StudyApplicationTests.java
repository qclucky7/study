package com.qingchen.study;

import com.qingchen.study.mail.MailBean;
import com.qingchen.study.mail.MailUtil1;
import com.qingchen.study.observer.ActivitySubjectCommon;
import com.qingchen.study.strategy.StrategyContext;
import com.qingchen.study.strategy.Vip3;
import com.qingchen.study.strategy.Vip4;
import com.qingchen.study.testclass.TestClass;
import com.qingchen.study.utils.IdUtils;
import com.qingchen.study.utils.JavaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class StudyApplicationTests {

    @Resource
    private ActivitySubjectCommon activitySubjectCommon;

    @Autowired
    private MailUtil1 mailUtil1;
    @Resource
    private StrategyContext strategyContext;
    @Resource
    private Vip3 vip3;
    @Resource
    private Vip4 vip4;
    @Resource
    private TestClass testClass;

    @Test
    void contextLoads() throws Exception{
        //testPhone();
        //testId();
        //testStrategy();
        //testClassMethod();
        testMail();
        //testSimMail();
    }

    public void test(){
        activitySubjectCommon.test(10);
    }

    public void testPhone(){

        long phone = 15242957219L;

        String phoneHide = JavaUtils.getPhoneHide(String.valueOf(phone));

        System.out.println(phoneHide);

    }

    public void testId(){
        String id = IdUtils.getId();
        System.out.println(id);
        String id1 = IdUtils.getId(10);
        System.out.println(id1);
        String wang = IdUtils.getId("wang");
        System.out.println(wang);
        String wang1 = IdUtils.getId("wang", 10);
        System.out.println(wang1);
    }

    public void testStrategy(){
        strategyContext.setStrategy(vip3).strategy();
        strategyContext.setStrategy(vip4).strategy();
    }

    public void testClassMethod(){

        Field[] fields = testClass.getClass().getFields();
        Field[] declaredFields = testClass.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        System.out.println(Arrays.toString(declaredFields));
    }

    public void testMail() throws Exception{
        MailBean mailBean = new MailBean();
        mailBean.setSubject("test 邮箱标题");
        mailBean.setText("这是邮箱内容");
        mailBean.setSendMailAddress(Arrays.asList("271724646@qq.com","Gsxz2020@126.com"));
        List<String> list1 = Arrays.asList("D:\\Huawei Share\\OneHop\\王晨.pdf", "D:\\Huawei Share\\OneHop\\2.mp4");
        List<String> list2 = Arrays.asList("这是王晨改名得.pdf", "这是MP4.mp4");
        mailBean.setFilePath(list1);
        mailBean.setAttachmentFilename(list2);
        mailUtil1.sendMailAttachment(mailBean);
    }

    public void testSimMail() throws Exception{
        mailUtil1.sendMail();
    }

}
