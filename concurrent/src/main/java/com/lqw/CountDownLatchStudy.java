package com.lqw;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author LQW
 * @date 2021-06-06 16:08
 **/
public class CountDownLatchStudy {
    public static void main(String[] args) {

        StartLine startLine=new StartLine(()->{
            System.out.println("FFFF");
        },5);

        startLine.prepare();

        startLine.run();

    }






    private static void simple() {
        CountDownLatch startGate=new CountDownLatch(1);

        startGate.countDown();
        System.out.println("startGate.countDown();");

        startGate.countDown();

        System.out.println("startGate.countDown();");
        System.out.println(startGate.getCount());
    }
}
