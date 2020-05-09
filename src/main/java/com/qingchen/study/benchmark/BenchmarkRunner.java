package com.qingchen.study.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @ClassName BenchmarkRunner
 * @description:
 * @author: WangChen
 * @create: 2020-04-01 12:01
 **/
public class BenchmarkRunner {

    public static void main(String[] args) throws RunnerException {

        /**
         *
         include
         benchmark 所在的类的名字，注意这里是使用正则表达式对所有类进行匹配的。

         fork
         进行 fork 的次数。如果 fork 数是2的话，则 JMH 会 fork 出两个进程来进行测试。

         warmUpIterations
         预热的迭代次数。

         measurementIterations
         实际测量的迭代次数。
         */
        Options opt = new OptionsBuilder()
                .include(CollectionBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
