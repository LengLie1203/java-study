package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * 交替打印字符串
 * <p>
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question_1195_1 {
    private int n;

    public Question_1195_1(int n) {
        this.n = n;
    }

    private  AtomicInteger i = new AtomicInteger(1);
    private Semaphore semaphore = new Semaphore(1, true);

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        while (i.get() <= n) {
            semaphore.acquire();
            if (i.get() % 3 == 0 && i.get() % 5 != 0 && i.get() <= n) {
                printFizz.run();
                i.getAndIncrement();
            }
            semaphore.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (i.get() <= n) {
            semaphore.acquire();
            if (i.get() % 3 != 0 && i.get() % 5 == 0 && i.get() <= n) {
                printBuzz.run();
                i.getAndIncrement();
            }
            semaphore.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (i.get() <= n) {
            semaphore.acquire();
            if (i.get() % 3 == 0 && i.get() % 5 == 0 && i.get() <= n) {
                printFizzBuzz.run();
                i.getAndIncrement();
            }
            semaphore.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

        while (i.get() <= n) {
            semaphore.acquire();
            if (i.get() % 3 != 0 && i.get() % 5 != 0 && i.get() <= n) {
                printNumber.accept(i.get());
                i.getAndIncrement();
            }
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Question_1195_1 question_1195 = new Question_1195_1(15);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(() -> {
            try {
                question_1195.number((i) -> System.out.println(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                question_1195.fizz(() -> {
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {

            try {
                question_1195.fizzbuzz(() -> {
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {

            try {
                question_1195.buzz(() -> {
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
