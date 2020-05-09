package com.qingchen.study.stream;

import com.qingchen.study.mail.MailBean;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName StreamTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-08 13:36
 **/
public class StreamTest {

    @Test
    public void testStream(){

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
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(MailBean::getSubject,
                        Collectors.mapping(MailBean::getText, Collectors.toList())));


        Map<String, Optional<MailBean>> collect1 = mailBeans.stream()
                .collect(Collectors.groupingBy(MailBean::getSubject,
                        Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(MailBean::getText)))));

        Map<String, MailBean> collect2 = mailBeans.stream().collect(Collectors.toMap(MailBean::getSubject,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(MailBean::getText)),
                TreeMap::new)
        );


        List<MailBean> mailBeans1 = mailBeans.stream().
                findAny()
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());

    }
}
