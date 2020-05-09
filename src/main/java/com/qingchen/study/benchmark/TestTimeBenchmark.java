package com.qingchen.study.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestTimeBenchmark
 * @description:
 * @author: WangChen
 * @create: 2020-04-01 13:47
 **/
@BenchmarkMode(value = Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 2)
@Measurement(batchSize = 30)
@Threads(value = 5)
public class TestTimeBenchmark {

    @Benchmark
    public void testSys(Blackhole blackhole){
        blackhole.consume(System.currentTimeMillis());
    }

    @Benchmark
    public void testSysnNow(Blackhole blackhole){
        blackhole.consume(System.nanoTime());
    }
}
