package com.lqw.onjava8;

/**
 * @author LQW
 * @date 2021-04-12 09:26
 **/
// onjava/TimedAbort.java
// Terminate a program after t seconds
import com.lqw.concurrent.Nap;

import java.util.concurrent.*;

public class TimedAbort {
    private volatile boolean restart = true;
    public TimedAbort(double t, String msg) {
        CompletableFuture.runAsync(() -> {
            try {
                while(restart) {
                    restart = false;
                    TimeUnit.MILLISECONDS
                            .sleep((int)(1000 * t));
                }
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(msg);
            System.exit(0);
        });
    }
    public TimedAbort(double t) {
        this(t, "TimedAbort " + t);
    }
    public void restart() { restart = true; }


    public static void main(String[] args) {
        new TimedAbort(1, "No odd numbers discovered");

//        new Nap(2);
    }
}

