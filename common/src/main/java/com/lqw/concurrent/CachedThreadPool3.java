package com.lqw.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-02 09:25
 **/
public class CachedThreadPool3 {

    public static Integer getResult(Future<Integer> future) {
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<CountingTask> tasks = IntStream.range(0, 10).mapToObj(CountingTask::new).collect(Collectors.toList());

        ExecutorService executorService= Executors.newCachedThreadPool();

        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);

            Integer result = futures.stream().map(CachedThreadPool3::getResult).reduce(0,Integer::sum);

            executorService.shutdown();
            System.out.println(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
