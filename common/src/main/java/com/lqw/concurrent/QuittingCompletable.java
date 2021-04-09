package com.lqw.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-02 11:39
 **/
public class QuittingCompletable {

    public static void main(String[] args) {
        List<QuittableTask> tasks = IntStream.range(0, QuittingTasks.COUNT)
                .mapToObj(QuittableTask::new).collect(Collectors.toList());

        List<CompletableFuture<Void>> futures = tasks.stream().map(CompletableFuture::runAsync)
                .collect(Collectors.toList());

        new Nap(1);

        tasks.forEach(QuittableTask::quit);

        futures.forEach(CompletableFuture::join);
    }
}
