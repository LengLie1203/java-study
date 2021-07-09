package com.lqw;

import java.util.concurrent.*;

public class FutureStudy {

    public static void main(String[] args) {
        Callable<String> callable= () -> {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            return "success";
        };


        Future<String> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            String s = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
        }
    }


}
