package com.lqw;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 起跑线，使用countDownLatch类实现的，让所有线程同时开始执行
 * @author LQW
 * @date 2021-06-06 16:50
 **/
public class StartLine {

   private final CountDownLatch startGate=new CountDownLatch(1);

   private Runnable task;

   private int count;

   private AtomicBoolean prepare;

     private List<Thread> threads= Lists.newArrayList();

    public StartLine(Runnable task, int count) {
        this.task = task;
        this.count = count;
        this.prepare=new AtomicBoolean(false);
    }


    public void prepare (){

        if (prepare.compareAndSet(false,true)){
            for (int i = 0; i < count; i++) {
                threads.add(new Thread(()->{
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    task.run();
                }));
            }
            System.out.println("prepare !!");
        }else {
            System.out.println("already !!");
        }
    }

    public void run(){

        threads.forEach(Thread::start);

        startGate.countDown();
    }
}
