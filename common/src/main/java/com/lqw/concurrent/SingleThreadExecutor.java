package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-01 22:02
 **/
public class SingleThreadExecutor {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10)
                .mapToObj(NapTask::new)
                .forEach(executorService::execute);

        System.out.println("All tasks submitted");
        executorService.shutdown();




        while (!executorService.isTerminated()) {
            System.out.println(
                    Thread.currentThread().getName() +
                            " awaiting termination");
            new Nap(0.1);
        }

    }
}
