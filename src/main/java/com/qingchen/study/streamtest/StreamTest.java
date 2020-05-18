package com.qingchen.study.streamtest;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @ClassName StreamTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-20 13:26
 **/
@Transactional(rollbackFor = Exception.class)
public class StreamTest {

    @Test
    public void myTest(){
        //造数据 这里就用罗翔老师得法外狂徒吧！
        List<ExpensesRecord> expensesRecords = Arrays.asList(
                new ExpensesRecord(1L, "法外狂徒张三", 100L, "天津", System.currentTimeMillis()),
                new ExpensesRecord(1L, "法外狂徒张三", 200L, "北京", System.currentTimeMillis()),
                new ExpensesRecord(1L, "法外狂徒张三", 300L, "天津", System.currentTimeMillis()),
                new ExpensesRecord(2L, "罗翔老师", 100L, "天津", System.currentTimeMillis()),
                new ExpensesRecord(2L, "罗翔老师", 200L, "北京", System.currentTimeMillis()),
                new ExpensesRecord(2L, "罗翔老师", 300L, "天津", System.currentTimeMillis()),
                new ExpensesRecord(3L, "冰清玉洁李四", 100L, "天津", System.currentTimeMillis()),
                new ExpensesRecord(3L, "冰清玉洁李四", 200L, "北京", System.currentTimeMillis()),
                new ExpensesRecord(3L, "冰清玉洁李四", 300L, "上海", System.currentTimeMillis()),
                new ExpensesRecord(3L, "冰清玉洁李四", 400L, "天津", System.currentTimeMillis())
        );

        //得到法外狂徒张三所有钱总和, stream有很多实现方式 也可以用Collector去实现
        long sum = expensesRecords.stream()
                .filter(expensesRecord -> "法外狂徒张三".equals(expensesRecord.getName()))
                //map传入一个Function 有输入有返回
                .mapToLong(ExpensesRecord::getMoney)
                .sum();

        //我想统计每个人的数量
        //result = {冰清玉洁李四=4, 法外狂徒张三=3, 罗翔老师=3}
        Map<String, Long> personCount = expensesRecords.stream()
                .collect(groupingBy(ExpensesRecord::getName,
                counting())
                );

        //我想统计一下每个人在哪些城市消费过 k->list
        //这里面没去重, 去重得话收集器去手机Set就可以了, 主要讲用法. Collectors.toSet()
        //result = {冰清玉洁李四=[天津, 北京, 上海, 天津], 法外狂徒张三=[天津, 北京, 天津], 罗翔老师=[天津, 北京, 天津]}
        Map<String, List<String>> personLocation = expensesRecords.stream()
                .collect(groupingBy(ExpensesRecord::getName,
                        //mapping就是去映射这个对象的一个属性, 然后在传一个收集器去收集
                        //对比一下如果你不写mapping的话它默认去分组的是key->对应一个对象而不是一个属性。
                       mapping(ExpensesRecord::getLocation,
                                collectingAndThen(toList(), Collections::unmodifiableList)))
                );
        //返回一个只读的list 大小为1
        List<String> list = Collections.singletonList("a");
        //返回一个不可变集合！！！！ Collectors.collectingAndThen配合这个使用 原来如此！
        List<ExpensesRecord> expensesRecords1 = Collections.unmodifiableList(expensesRecords);


        //我想统计一下每个人消费的钱最多的那条记录 两种方法可以用分组也可以用map
        //method1 result =
        //         {
        //          冰清玉洁李四=Optional[ExpensesRecord{id=3, name='冰清玉洁李四', money=400, location='天津', time=1587362422628}],
        //          法外狂徒张三=Optional[ExpensesRecord{id=1, name='法外狂徒张三', money=300, location='天津', time=1587362422628}],
        //          罗翔老师=Optional[ExpensesRecord{id=2, name='罗翔老师', money=300, location='天津', time=1587362422628}]
        //          }
        //method2 result =
        //         {
        //          冰清玉洁李四=ExpensesRecord{id=3, name='冰清玉洁李四', money=400, location='天津', time=1587362689613},
        //          法外狂徒张三=ExpensesRecord{id=1, name='法外狂徒张三', money=300, location='天津', time=1587362689613},
        //          罗翔老师=ExpensesRecord{id=2, name='罗翔老师', money=300, location='天津', time=1587362689613}
        //          }
        Map<String, Optional<ExpensesRecord>> method1 = expensesRecords.stream()
                .collect(groupingBy(ExpensesRecord::getName,
                        Collectors.reducing(
                                BinaryOperator.maxBy(Comparator.comparingLong(ExpensesRecord::getMoney))
                        ))
                );
        Map<String, ExpensesRecord> method2 = expensesRecords.stream()
                .collect(Collectors.toMap(ExpensesRecord::getName,
                        Function.identity(), //return t -> t; 这就是返回本身
                        BinaryOperator.maxBy(Comparator.comparingLong(ExpensesRecord::getMoney))
                ));


        //我想查找各个人消费的钱数总和, 平均, 最大值, 最小值, 数量！
        Map<String, LongSummaryStatistics> summaryStatistics = expensesRecords.stream()
                .collect(groupingBy(ExpensesRecord::getName,
                        summarizingLong(ExpensesRecord::getMoney)));
        long count = summaryStatistics.get("法外狂徒张三").getCount();
        double average = summaryStatistics.get("法外狂徒张三").getAverage();
        long max = summaryStatistics.get("法外狂徒张三").getMax();
        long min = summaryStatistics.get("法外狂徒张三").getMin();
        long sum1 = summaryStatistics.get("法外狂徒张三").getSum();
        //只想统计总数
        Map<String, Long> summingLong = expensesRecords.stream()
                .collect(groupingBy(ExpensesRecord::getName,
                        Collectors.summingLong(ExpensesRecord::getMoney)));


        //排序
        // 这里单独拿出来 因为这个可以配合所有的方式去实现不同的过滤
        expensesRecords.stream()
                //按钱的降序去排, 钱数相等按时间去降序排序, 钱数还相同的话按id升序,
                // 这里降序升序传一个Comparator.reverseOrder() 也可以.reversed()
                //注意使用.reversed()的时候 如果只在最后一个使用只能达到最后一个倒序排序, 不会影响原来的排序！
                .sorted(Comparator.comparing(ExpensesRecord::getMoney, Comparator.reverseOrder())
                        .thenComparing(ExpensesRecord::getTime, Comparator.reverseOrder())
                        .thenComparing(ExpensesRecord::getId)
                );

        //在stream配合Optional做元素处理
        //我们选一个上的例子 比如我们不确定是否会有null的情况, 在stream中判断
        expensesRecords.stream()
               .sorted(Comparator.comparing(expensesRecord ->
                       Optional.ofNullable(expensesRecord.getMoney()).orElse(0L),
                       Comparator.reverseOrder()
               ));

        //总结 比如map, filter等我就不介绍了, 在例子上面添加实现相关功能就可以

    }

}
