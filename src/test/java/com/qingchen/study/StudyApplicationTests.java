package com.qingchen.study;

import com.qingchen.study.elasticsearch.ArticleRepository;
import com.qingchen.study.elasticsearch.entity.Article;
import com.qingchen.study.properties.PropertiesTest;
import com.qingchen.study.utils.IdUtils;
import com.qingchen.study.utils.JavaUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootTest
class StudyApplicationTests {


    @Autowired
    private PropertiesTest propertiesTest;

    @Test
    void contextLoads() throws Exception{
        //testPhone();
        //testId();
        //testStrategy();
        //testClassMethod();
        //testMail();
        //testSimMail();
       // Role role = new Role();
        //role.setPrivilege(Privilege.visiter);
        //filterManager.checkPrivilege(new String());\
        //filterManager.checkPrivilege(new Custom());
        //filterManager.filterEntity(role);
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> filterManager.checkPrivilege(new Custom()));
//        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> filterManager.checkPrivilege(role));
//        CompletableFuture.allOf(voidCompletableFuture, voidCompletableFuture1);

//        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
//        Map<String, Filter> beansOfType = applicationContext.getBeansOfType(Filter.class);
//        System.out.println(beansOfType.toString());

        //testApplication();



    }


    public void testApplication(){

    }

    public void test(){
        //activitySubjectCommon.test(10);
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
        //strategyContext.setStrategy(vip3).strategy();
        //strategyContext.setStrategy(vip4).strategy();
    }

    public void testClassMethod(){

//        Field[] fields = testClass.getClass().getFields();
//        Field[] declaredFields = testClass.getClass().getDeclaredFields();
//        System.out.println(Arrays.toString(fields));
//        System.out.println(Arrays.toString(declaredFields));
    }

//    public void testMail() throws Exception{
//        MailBean mailBean = new MailBean();
//        mailBean.setSubject("test 邮箱标题");
//        mailBean.setText("这是邮箱内容");
//        mailBean.setSendMailAddress(Arrays.asList("271724646@qq.com","Gsxz2020@126.com"));
//        List<String> list1 = Arrays.asList("D:\\Huawei Share\\OneHop\\王晨.pdf", "D:\\Huawei Share\\OneHop\\2.mp4");
//        List<String> list2 = Arrays.asList("这是王晨改名得.pdf", "这是MP4.mp4");
//        mailBean.setFilePath(list1);
//        mailBean.setAttachmentFilename(list2);
//        mailUtil1.sendMailAttachment(mailBean);
//    }
//
//    public void testSimMail() throws Exception{
//        mailUtil1.sendMail();
//    }

}
