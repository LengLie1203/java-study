package com.lqw.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class Question_1116 {

    private int n;

    public Question_1116(int n) {
        this.n = n;
    }

    private volatile int j=1;
    private Semaphore zero=new Semaphore(0);
    private Semaphore even=new Semaphore(0);
    private Semaphore odd=new Semaphore(1);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (; j <= n; ) {
            zero.release();
            even.acquire();
            printNumber.accept(j);
            j++;
            odd.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for ( ; j <= n;  ) {
            zero.release();
            odd.acquire();
            printNumber.accept(0);
            j++;
            even.release();
        }
    }
}