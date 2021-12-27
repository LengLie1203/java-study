package com.lqw;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTimeOutTest {

    public static void main(String[] args) {
        FutureTask<String> futureTask=new FutureTask<String>(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println("11111");
                Thread.sleep(1000);
            }
            return "HHHHHH";
        });

        new Thread(()->futureTask.run()).start();

        try {
            futureTask.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("timeout");
            futureTask.cancel(true);
        }

        System.out.println("NNNNNNNN");

    }
}
