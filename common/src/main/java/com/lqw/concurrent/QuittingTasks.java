package com.lqw.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-02 11:27
 **/
public class QuittingTasks {

    public static final int COUNT=150;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<QuittableTask> tasks = IntStream.range(0, 150).mapToObj(QuittableTask::new)
                .peek(executorService::execute).collect(Collectors.toList());

        new Nap(1);
        tasks.forEach(QuittableTask::quit);

        executorService.shutdown();
    }
}
