package com.qingchen.study.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LockBenchmark
 * @description:
 * @author: WangChen
 * @create: 2020-04-07 17:30
 **/
@BenchmarkMode(value = Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1)
@Measurement(batchSize = 30)
@Threads(value = 10)
public class LockBenchmark {

    private List<Integer> list = new ArrayList<>();

    private static final Object object = new Object();



}
