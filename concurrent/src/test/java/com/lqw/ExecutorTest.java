package com.lqw;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorTest {

    @Test
    public void invokeAll() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            long sta=System.currentTimeMillis();
            List<Future<String>> futures = executorService.invokeAll(IntStream.range(1, 10).mapToObj(TimingCallable::new)
                    .collect(Collectors.toList()), 5, TimeUnit.SECONDS);
            long end=System.currentTimeMillis();
            System.out.println("cost "+(end-sta));

            futures.forEach(future-> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }


    @Test
    public void name() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new TimingRunnable(10));
        System.out.println("is execute");

        executorService.shutdownNow();
        System.out.println("is shutdown");

//        try {
//            executorService.awaitTermination(3,TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("termination");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
