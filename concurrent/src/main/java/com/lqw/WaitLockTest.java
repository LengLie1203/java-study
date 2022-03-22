package com.lqw;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WaitLockTest {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);


        Future<String> submit = executorService.submit(() -> {
            return "FFFFF";
        });

    }
}
