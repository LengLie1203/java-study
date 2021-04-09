package com.lqw.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author LQW
 * @date 2021-04-02 09:35
 **/
public class Futures {
    public static void main(String[] args)throws InterruptedException, ExecutionException {
        ExecutorService exec
                = Executors.newSingleThreadExecutor();
        Future<Integer> f =
                exec.submit(new CountingTask(99));
        System.out.println(f.get()); // [1]
        exec.shutdown();
    }
}
