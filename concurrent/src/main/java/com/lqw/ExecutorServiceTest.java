package com.lqw;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorServiceTest {

    public static void main(String[] args) {
        Executor executor=Executors.newFixedThreadPool(4);

        CompletionService<String> completionService =new ExecutorCompletionService<>(executor);

        List<Callable<String>> jobs = IntStream.range(1, 10).mapToObj(i -> (Callable<String>) () -> {
            try {
                Thread.sleep(1000*i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i + "";
        }).collect(Collectors.toList());

        List<Future<String>> futures = jobs.stream().map(completionService::submit).collect(Collectors.toList());

        try {

            for (int i = 0; i < jobs.size(); i++) {

                String s = completionService.take().get();

                System.out.println("getresult "+s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
