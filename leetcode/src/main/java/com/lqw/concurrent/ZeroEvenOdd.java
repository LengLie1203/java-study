package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }
    private AtomicInteger j=new AtomicInteger(1);
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
        for (; j.get() <= n; ) {
            zero.release();
            even.acquire();
            printNumber.accept(j.getAndAdd(1));

            odd.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for ( ; j.get() <= n;  ) {
            zero.release();
            odd.acquire();
            printNumber.accept(j.getAndAdd(1));
            even.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd=new ZeroEvenOdd(5);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(()-> {
            try {
                zeroEvenOdd.zero((int i)-> System.out.printf(i+""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(()-> {
            try {
                zeroEvenOdd.even((int i)-> System.out.printf(i+""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(()-> {
            try {
                zeroEvenOdd.odd((int i)-> System.out.printf(i+""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}