package com.qingchen.study.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName StreamBenchmark
 * @description:
 * @author: WangChen
 * @create: 2020-04-01 13:09
 **/
@BenchmarkMode(value = Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1)
@Measurement(batchSize = 30)
@Threads(value = 10)
public class StreamBenchmark {

    private List<Integer> list = new ArrayList<>();

    @Setup
    public void setup(){
        for (int i = 1; i <= 10000 ; i++) {
            list.add(i);
        }

    }

    @Benchmark
    public List testList(){
        List<Integer> newList = new ArrayList<>();
        for (Integer integer : list) {
            if (integer/2 == 0){
                newList.add(integer);
            }
        }
        return newList;
    }

    @Benchmark
    public List testStreamList(){
       return list.stream().filter(integer -> integer/2 ==0).collect(Collectors.toList());
    }


}
