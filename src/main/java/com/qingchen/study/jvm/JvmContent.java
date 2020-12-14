package com.qingchen.study.jvm;

/**
 * @ClassName JvmContent
 * @description:
 * @author: WangChen
 * @create: 2020-04-08 15:00
 **/
public class JvmContent {


    //*********************jvm参数********************************//
    // xx参数   boolean类型  -XX: + / -   是否开启
    //         kv设值类型 -XX: 属性key = 属性值value 例: -XX: MetaspaceSize = 1024M
    //         -Xms 等价于 -XX:InitialHeapSize 初始内存大小
    //         -Xmx 等价于 -XX:MaxHeapSize    最大内存
    //         -Xss 等价于 -XX:ThreadStackSize 线程栈大小
    //         -Xmn 等价于新生代大小
    //         -XX: MateSpaceSize1024m 原空间使用本地内存 (jvm默认值用java -XX:+PrintFlagsFinal -version 查看！！)
    //         -XX:+PrintGCDetails 开始gc回收日志
    // Ratio:比例  -XX:SurvivorRatio 设置新生代 eden:s0:s1区比例 默认8:1:1 -XX:SurvivorRatio=8
    //         -XX:NewRatio 设置新生代和老年代的比例 默认-XX:NewRatio=2 表示新生代1老年代2
    //         -XX:MaxTenuringThreshold 设置代龄 默认15区间在(0~15) 代表在新生区经过多少的GC后可以进入老年区

    // /****调优****/
    // java -XX:+PrintFlagsInitial 查看初始参数
    // java -XX:+PrintFlagsFinal      /  -version
    // := 代表有被改过  没有:代表是出厂值
    // java -XX:+PrintCommandLineFlags -version 查看相关参数 。 可以看使用的垃圾回收器
    //


    // ******************java相关指令****************************//
    //jps -l 查看java进程
    //jinfo -flag xxxx pid 查看运行的java程序指定信息
    //jinfo -flags pid 查看运行的java程序的所有信息
    //jstack 进程号

    //linux上查看jvm相关信息。
    // jstat -gcutil <pid> 2000 100 # 每2秒输出一次内存情况，连续输出100次
    // jstat -gc pid 1000
    // jstat -gccause pid 1000 查看gc原因

    //生成dump文件的命令：
    //jmap -dump:format=b,file=20170307.dump pid
    //jmap -dump:format=b,file=heapdump.hprof pid
    //启动命令
    //nohup java -Xms512m -Xmx512m -jar quartz-1.0.0.jar


    //jvisualvm 查看jvm监控


    //top -H -p [pid] 查看某个进程的线程tid（十进制）
    //使用printf “%x\n” tid 命令 转换为十六进制
    //使用 jstack [pid] > [pid].txt 查看线程dump ,定位查询上述tid占用请求状况 追加写入
    //jstack pid | grep 转后的线程id



    /************************************ G C ************************************/

    // 1. gcRoot 可达性分析 --------> 可以成为root节点的有  {   1. 虚拟机栈局部变量区
    //                                                      2. 方法区类静态属性引用的对象
    //                                                      3. 方法区常量引用的对象
    //                                                      2. 本地方法栈引用的对象
    //                                                   }
    //<---------------查看GC流程--------------------->
    // java -XX:+PrintCommandLineFlags -version 查看相关参数 。 可以看使用的垃圾回收器
    // <--------YoungGC---------------->
    //   GC类型
    // [GC (Allocation Failure)
    //   年轻代的gc   GC前内存占用->GC后内存占用(Young区总大小)   GC前堆内存占用->GC后堆内存占用(JVM堆内存总大小) YoungGC耗时
    // [PSYoungGen:      2048K->504K(2560K)]                 2048K->957K(9728K), 0.0016739 secs]
    //        用户耗时    YoungGC系统耗时  真实耗时
    // [Times: user=0.00 sys=0.00, real=0.00 secs]
    // <-----------FULL GC------------->
    //      GC类型
    // [Full GC (Allocation Failure)
    //   FullGC前 Young区小大->GC后大小(Young区总大小)
    // [PSYoungGen: 504K->0K(2560K)]
    //   FullGC前old区大小->GC后大小(old总大小)  堆GC前大小->GC后大小(总大小)  元空间GC前大小->GC后大小(总大小)
    // [ParOldGen: 1145K->1272K(7168K)] 1649K->1272K(9728K), [Metaspace: 3339K->3339K(1056768K)], 0.0050230 secs]
    // [Times: user=0.00 sys=0.00, real=0.00 secs]
    //
    // GC收集器
    // 1. serial串行收集器
    // 2. parNew并行收集器  只影响新生代
    // 3. parallel Scavenge java8默认并行收集器 默认老年代也并行
    // 4. CMS 并发标记清除收集器  是一种最短回收停顿时间得收集器
    //    适合堆内存大, cup核数多的服务器应用。
    //  CMS过程:  1. 初始标记 (标记一下GC Roots能直接关联的第一层对象)  需要暂停所有线程
    //           2. 并发标记 (从标记过的对象继续出发,关联的继续标记) 用户线程和GC线程一起  不需要暂停所有线程
    //           3. 重新标记 (修正在并发标记过程中用户运行导致的标记变动) 需要暂停所有线程
    //           4. 标记清除 清除标记的对象  用户线程和GC线程一起  不需要暂停所有线程
    // 5. G1垃圾回收器
    //     G1整体上采用标记整理的算法。
    //     将新生代和老年代划分成一个个的独立的子区域(Region)
}
