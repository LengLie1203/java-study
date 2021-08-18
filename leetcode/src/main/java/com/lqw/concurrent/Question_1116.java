package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 打印零与奇偶数
 */
public class Question_1116 {
    private int n;

    public Question_1116(int n) {
        this.n = n;
    }
    private Semaphore zero=new Semaphore(1);
    private Semaphore even=new Semaphore(0);
    private Semaphore odd=new Semaphore(0);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i%2==0){
                even.release();
            }else {
                odd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i=2;i<=n;i+=2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i=1;i<=n;i+=2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {
        Question_1116 zeroEvenOdd=new Question_1116(12);

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