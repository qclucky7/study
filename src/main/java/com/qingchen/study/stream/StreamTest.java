package com.qingchen.study.stream;

import com.qingchen.demo.mydemo.model.Message;
import com.qingchen.study.mail.MailBean;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;

/**
 * @ClassName StreamTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-08 13:36
 **/
@Configuration
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
                .collect(groupingBy(MailBean::getSubject,
                         mapping(MailBean::getText, toList())));

        System.out.println(collect.toString());

        Map<String, Long> collect3 = mailBeans.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(MailBean::getSubject,
                        mapping(MailBean::getText, counting())));

        Map<String, String> collect4 = mailBeans.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(MailBean::getSubject,
                        mapping(MailBean::getText, joining(",", "{", "}"))));

        System.out.println(collect4.toString());


        Map<String, Optional<MailBean>> collect1 = mailBeans.stream()
                .collect(groupingBy(MailBean::getSubject, reducing(BinaryOperator.maxBy(comparing(MailBean::getText)))));

        Map<String, MailBean> collect2 = mailBeans.stream().collect(Collectors.toMap(MailBean::getSubject,
                Function.identity(),
                BinaryOperator.maxBy(comparing(MailBean::getText)),
                TreeMap::new)
        );


        List<MailBean> mailBeans1 = mailBeans.stream().
                findAny()
                .map(Collections::singletonList)
                .orElse(emptyList());

    }

    @Test
    public void myTest(){
        List<Character> list = new ArrayList<>();
        String s = "aaaabbbc,c:c.c我ccdd我我我dddd来来dddd乐乐了ddddd";
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            list.add(aChar);
        }


        Map<Character, Long> collect = list.stream()
                .filter(this::check)
                .collect(groupingBy(Function.identity(),myMapSupplier, counting())
                );
        System.out.println(collect.toString());
    }

    Supplier<TreeMap<Character, Long>> myMapSupplier = TreeMap::new;

    private boolean check(Character character){
        char c = ",".charAt(0);
        char c1 = ".".charAt(0);
        char c2 = ":".charAt(0);
        return !character.equals(c) && !character.equals(c1) && !character.equals(c2);
    }

    @Test
    public void myTest2(){

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("key", 1);
        hashMap.put("key1",2);
        HashMap<String, Integer> hashMap1 = new HashMap<>();
        hashMap1.put("key", 1);
        hashMap1.put("key1",2);
        hashMap1.put("key2",25);
        hashMap1.put("key3",25);
        hashMap1.put("aey3",25);

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String k1 = entry.getKey();
            Integer k2 = entry.getValue();
        }

        Supplier<TreeMap<String, Integer>> treeMap = () -> new TreeMap<>(Comparator.reverseOrder());

        Map<String, Integer> collect = Stream.of(hashMap, hashMap1)
                .flatMap(stringIntegerHashMap -> stringIntegerHashMap.entrySet().stream())
                //.sorted(comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum,
                        LinkedHashMap::new
                        )
                );

        System.out.println(collect.toString());

        Map<String, Integer> collect1 = collect
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v2,
                        LinkedHashMap::new
                        )
                );


        System.out.println(collect1.toString());




    }

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size, int countSize) {
        return (size + countSize - 1) / countSize;
    }


    @Test
    public void myTest3(){

        String ss = "1234567890ABCDEF";
        List<String> strList = getStrList(ss, 2);
        String[][] strings = Stream.iterate(0, n -> n + 1)
                .limit(strList.size())
                .map(x -> strList.stream()
                        .skip(x)
                        .limit(1)
                        .toArray(String[]::new)
                ).toArray(String[][]::new);
        System.out.println(Arrays.deepToString(strings));
    }
    private static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    private static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }
    private static String substring(String str, int f, int t) {
        if (f > str.length()){
            return null;
        }
        if (t > str.length()) {
            return str.substring(f);
        } else {
            return str.substring(f, t);
        }
    }
}
