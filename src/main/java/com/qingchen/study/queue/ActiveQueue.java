package com.qingchen.study.queue;

import com.qingchen.demo.mydemo.mapper.master.TestMapper;
import com.qingchen.demo.mydemo.model.Message;
import com.qingchen.study.application.ApplicationContextProvider;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @ClassName ActiveQueue
 * @description:
 * @author: WangChen
 * @create: 2020-04-26 12:48
 **/
public class ActiveQueue{

    private static BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>(2048);

//    @Resource
//    private TestMapper mapper;

    protected ExecutorService executor;

    private static class ActiveQueueProvider{
        private static ActiveQueue activeQueue =
                ApplicationContextProvider.getApplicationContext().getBean(ActiveQueue.class);
    }

    public static ActiveQueue getInstance(){
        return ActiveQueueProvider.activeQueue;
    }

    public void pushMessage(Message message){
        try {
            blockingQueue.offer(message, 200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("ActiveQueue加载....");
        executor.execute(this::run);
    }

    private void run() {
        do {
            try {
                handleMessage(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private void handleMessage (Message message){
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程消费" + Thread.currentThread().getName() + "信息:" + message.toString());;
        //mapper.insert(message);
    }


}
