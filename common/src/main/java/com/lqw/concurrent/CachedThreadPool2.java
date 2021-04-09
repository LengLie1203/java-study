package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-01 22:29
 **/
public class CachedThreadPool2 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 10)
                .mapToObj(InterferingTask::new)
                .forEach(executorService::execute);

        executorService.shutdown();
    }
}
