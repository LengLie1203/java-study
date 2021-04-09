package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author LQW
 * @date 2021-04-01 22:20
 **/
public class MoreTasksAfterShutdown {
    public static void main(String[] args) {
        ExecutorService exec
                = Executors.newSingleThreadExecutor();
        exec.execute(new NapTask(1));
        exec.shutdown();
        try {
            exec.execute(new NapTask(99));
        } catch (RejectedExecutionException e) {
            System.out.println(e);
        }
    }
}
