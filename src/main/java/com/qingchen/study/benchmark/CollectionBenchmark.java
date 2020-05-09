package com.qingchen.study.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName collection
 * @description:
 * @author: WangChen
 * @create: 2020-04-07 08:59
 **/
@BenchmarkMode(value = Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1)
@Measurement(batchSize = 3)
//@Threads(value = 3)
public class CollectionBenchmark {

    private static Set<Object> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
    private static Set<Object> newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static Set<Object> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
    private List<Object> arrList = new ArrayList<>();
    private List<Object> linkList = new LinkedList<>();

    @Benchmark
    public boolean testArryList(){
        for (int i = 0; i < 3000 ; i++) {
            arrList.add(i);
        }
        return true;
    }

    @Benchmark
    public boolean testLinkList(){
        for (int i = 0; i < 3000 ; i++) {
            linkList.add(i);
        }
        return true;
    }

//    @Benchmark
//    public boolean testCollectionSyc(){
//
//        for (int i = 0; i < 100 ; i++) {
//            synchronizedSet.add(i);
//        }
//        for (int i = 0; i < 100 ; i++) {
//            synchronizedSet.remove(i);
//        }
//        handleRead(synchronizedSet);
//        return true;
//    }
//    @Benchmark
//    public boolean testCollectionMapToSet(){
//
//        for (int i = 0; i < 100 ; i++) {
//            newSetFromMap.add(i);
//        }
//        for (int i = 0; i < 100 ; i++) {
//            newSetFromMap.remove(i);
//        }
//        handleRead(newSetFromMap);
//        return true;
//    }
//    @Benchmark
//    public boolean testCopyOnWriteArraySet(){
//
//        for (int i = 0; i < 100 ; i++) {
//            copyOnWriteArraySet.add(i);
//        }
//        for (int i = 0; i < 100 ; i++) {
//            copyOnWriteArraySet.remove(i);
//        }
//        handleRead(copyOnWriteArraySet);
//        return true;
//    }
//
//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
//    private static void handleRead(Set set){
//        set.forEach(i ->{
//
//        });
//    }


}
