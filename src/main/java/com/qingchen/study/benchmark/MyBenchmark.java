package com.qingchen.study.benchmark;

/**
 * @ClassName Benchmark
 * @description: 基准测试
 * @author: WangChen
 * @create: 2020-04-01 10:40
 **/

import org.omg.CORBA.portable.ValueOutputStream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Mode
 * Mode 表示 JMH 进行 Benchmark 时所使用的模式。通常是测量的维度不同，或是测量的方式不同。目前 JMH 共有四种模式：
 * Throughput: 整体吞吐量，例如“1秒内可以执行多少次调用”。
 * AverageTime: 调用的平均时间，例如“每次调用平均耗时xxx毫秒”。
 * SampleTime: 随机取样，最后输出取样结果的分布，例如“99%的调用在xxx毫秒以内，99.99%的调用在xxx毫秒以内”
 * SingleShotTime: 以上模式都是默认一次 iteration 是 1s，唯有 SingleShotTime 是只运行一次。往往同时把 warmUp 次数设为0，
 * 用于测试冷启动时的性能。
 *
 * Iteration
 * Iteration 是 JMH 进行测试的最小单位。在大部分模式下，一次 iteration 代表的是一秒，
 * JMH 会在这一秒内不断调用需要 benchmark 的方法，然后根据模式对其采样，计算吞吐量，计算平均执行时间等
 *
 * WarmUp
 * WarmUp 是指在实际进行 benchmark 前先进行预热的行为。为什么需要预热？因为 JVM 的 JIT 机制的存在，
 * 如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度。所以为了让 benchmark 的结果更加接近真实情况就需要进行预热。
 */
@BenchmarkMode(value = Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 2)
@Threads(20)
@Measurement(batchSize = 30)
public class MyBenchmark {

    @Benchmark
    public int testRandom(){
        Random random = new Random();
        return random.nextInt(10000);
    }
    @Benchmark
    public int testThreadLocalRandom(){
        return ThreadLocalRandom.current().nextInt(10000);
    }


    /**
     * @Param
     * @Param 可以用来指定某项参数的多种情况。特别适合用来测试一个函数在不同的参数输入的情况下的性能。
     * @Setup
     * @Setup 会在执行 benchmark 之前被执行，正如其名，主要用于初始化。
     * @TearDown
     * @TearDown 和 @Setup 相对的，会在所有 benchmark 执行结束以后执行，主要用于资源的回收等。
     *
     * 如果测试返回值是void得时候 Blackhole (黑洞, 有意思！) 传入  Blackhole.consume去消费
     */
//    @Benchmark
//    public List testList(){
//        return Collections.emptyList();
//    }
//
//    @Benchmark
//    public List testArrayList(){
//        return new ArrayList(0);
//    }
//
//    @Benchmark
//    public void testVoid(Blackhole blackhole){
//        blackhole.consume(Math.random());
//    }


//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
//    public List nullList(){
//        return null;
//    }
//
//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
//    public List notnullList(){
//        return Collections.emptyList();
//    }
//
//    @Benchmark
//    public boolean testIf(){
//       if (nullList() != null){
//           return true;
//       }
//       return false;
//    }
//
//    @Benchmark
//    public List testNotIf(){
//        return notnullList();
//    }




}
