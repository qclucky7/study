package com.qingchen.study.lock;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReentrantLockTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-07 10:00
 **/
public class ReentrantLockTest {

    private  static int j=0;

    public static void main(String[] args){


        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        AtomicReference<Object> objectAtomicReference = new AtomicReference<>();
        AtomicStampedReference<Object> objectAtomicStampedReference = new AtomicStampedReference<>(100,1);

        ReentrantLock reentrantLock = new ReentrantLock(true);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ExecutorService pool = Executors.newFixedThreadPool(5);



        //AbstractOwnableSynchronizer 维护一个当前Thread对象 是AbstractQueuedSynchronizer的父类

        //new ReentrantLock(true);
        // true为公平锁, 不传默认为非公平锁
        //不同的区别
//        protected final boolean tryAcquire(int acquires) {
//            final Thread current = Thread.currentThread();
//            int c = getState();
//            if (c == 0) {
                  //!hasQueuedPredecessors() 公平锁和非公平锁区别. 判断当前阻塞队列有没有元素, 公平锁会排到队尾
//                if (!hasQueuedPredecessors() &&
//                        compareAndSetState(0, acquires)) {
//                    setExclusiveOwnerThread(current);
//                    return true;
//                }
//            }
              //可重入锁逻辑, 当前线程再次拿到锁 重入次数++
//            else if (current == getExclusiveOwnerThread()) {
//                int nextc = c + acquires;
//                if (nextc < 0)
//                    throw new Error("Maximum lock count exceeded");
//                setState(nextc);
//                return true;
//            }
//            return false;
//        }

          //公平锁和非公平锁都会调用这个方法
          //尝试获取没有获得锁, tryAcquire执行false的话,
          //执行addWaiter方法 为当前线程和给定模式创建并排队节点 返回一个Node给acquireQueued使用
          //
//        public final void acquire(int arg) {
//            if (!tryAcquire(arg) &&
//                    acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
//                selfInterrupt();
//        }

         //如果!tryAcquire为true的话执行这段逻辑  把当前线程包装成Node节点 放到链表队尾
//        private Node addWaiter(Node mode) {
//            Node node = new Node(Thread.currentThread(), mode);
//            // Try the fast path of enq; backup to full enq on failure
//            Node pred = tail;
//            if (pred != null) {
//                node.prev = pred;
//                if (compareAndSetTail(pred, node)) {
//                    pred.next = node;
//                    return node;
//                }
//            }
//            enq(node);
//            return node;
//        }

         //enq循环将Node节点放到队尾。
//        private Node enq(final Node node) {
//            for (;;) {
//                Node t = tail;
//                if (t == null) { // Must initialize
//                    if (compareAndSetHead(new Node()))
//                        tail = head;
//                } else {
//                    node.prev = t;
//                    if (compareAndSetTail(t, node)) {
//                        t.next = node;
//                        return t;
//                    }
//                }
//            }
//        }

          //然后执行↓
          //阻塞队列(自旋)
//        final boolean acquireQueued(final Node node, int arg) {
//            boolean failed = true;
//            try {
//                boolean interrupted = false;
//                for (;;) {
//                    final Node p = node.predecessor();
                      //这判断当前节点的前驱节点是不是头节点, 如果是就开始tryAcquire尝试拿锁
                      //对比一下如果当前节点的前驱节点不是头节点, 那么&&第一个false就直接往下执行了。
//                    if (p == head && tryAcquire(arg)) {
//                        setHead(node);
//                        p.next = null; // help GC
//                        failed = false;
//                        return interrupted;
//                    }
//                    if (shouldParkAfterFailedAcquire(p, node) &&
//                            parkAndCheckInterrupt())
//                        interrupted = true;
//                }
//            } finally {
//                if (failed)
//                    cancelAcquire(node);
//            }
//        }

//        for (int i = 0; i < 5 ; i++) {
//            pool.submit(() -> {
//                reentrantReadWriteLock.readLock().lock();
//                reentrantReadWriteLock.writeLock().lock();
//                try{
//                    TimeUnit.SECONDS.sleep(3);
//                    j++;
//                }
//                catch (Exception e){
//
//                }
//                finally {
//                    //reentrantLock.unlock();
//                    reentrantReadWriteLock.readLock().unlock();
//                    reentrantReadWriteLock.writeLock().unlock();
//                    System.out.println(j);
//                }
//            });
//        }



        //


    }
}
