package com.qingchen.study.benchmark;

import com.qingchen.study.filter.booleanfilter.annotation.InitializeOrder;
import com.qingchen.study.filter.booleanfilter.filter.Filter;
import com.qingchen.study.filter.booleanfilter.filter.FilterChain;
import io.netty.util.internal.ThreadLocalRandom;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName sortBenchmark
 * @description:
 * @author: WangChen
 * @create: 2020-04-02 13:42
 **/
public class SortBenchmark {

    @Benchmark
    public List<Integer> testSort(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            list.add(ThreadLocalRandom.current().nextInt(1000));
        }
        return list.stream().sorted(Integer::compare).collect(Collectors.toList());
    }


    @Benchmark
    public List<Integer> testMySort(){
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            handleSort(integers, ThreadLocalRandom.current().nextInt(1000));
        }
        return integers;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private static void handleSort(List<Integer> list, Integer i){

        int index = 0;
        for (; index < list.size(); index++){
            if (list.get(index) > i){
                break;
            }
        }
        list.add(index, i);
    }
}
