package com.lqw;

import org.junit.Test;

import java.util.concurrent.*;

public class CompletableFutureTest {

    @Test
    public void name() throws InterruptedException {
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            try {
                return new TimingCallable(5).call();
            } catch (Exception e) {
                e.printStackTrace();
                throw new NullPointerException();
            }
        });

        try {
            String s = completableFuture.get(2, TimeUnit.SECONDS);
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("end");


        CompletableFuture.allOf(completableFuture).join();
    }
}
