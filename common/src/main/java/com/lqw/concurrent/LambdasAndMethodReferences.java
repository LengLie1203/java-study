package com.lqw.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author LQW
 * @date 2021-04-02 09:45
 **/

class NotRunnable{
    public void go(){
        System.out.println("NotRunnable.go()");
    }
}

class NotCallable{
    public Integer get(){
        System.out.println("NotCallable.get()");
        return 1;
    }
}

public class LambdasAndMethodReferences {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(()-> System.out.println("Lambda1"));

        executorService.submit(new NotRunnable()::go);

        executorService.submit(()->{
            System.out.println("Lambda2");
            return 2;
        });

        NotCallable notCallable = new NotCallable();
        Future<Integer> submit = executorService.submit(notCallable::get);

        System.out.println(submit.get());

        executorService.shutdown();

    }
}
