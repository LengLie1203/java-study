package com.lqw.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
public class Question_1195_2 {
    private int n;
    private int i = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Question_1195_2(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (i <= n) {
            lock.lock();
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
                i++;
                condition.signalAll();
            } else {
                condition.await();
            }
            lock.unlock();
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (i <= n) {
            lock.lock();
            if (i % 5 == 0 && i % 3 != 0) {
                printBuzz.run();
                i++;
                condition.signalAll();
            } else {
                condition.await();
            }
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (i <= n) {
            lock.lock();
            if (i % 3 == 0 && i % 5 == 0) {
                printFizzBuzz.run();
                i++;
                condition.signalAll();
            } else {
                condition.await();
            }
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            //fixme 这里
//            if(i==15){
//                System.out.println("fffff");
//                Thread.sleep(100);
            // will output 16
//            }
            lock.lock();
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                i++;
                condition.signalAll();
            } else {
                condition.await();
            }
            lock.unlock();

        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        Question_1195_2 question_1195 = new Question_1195_2(15);

        executorService.execute(() -> {
            try {
                question_1195.number((i) -> System.out.printf(i + " "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                question_1195.fizz(() -> {
                    //   System.out.printf("fizz ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {

            try {
                question_1195.fizzbuzz(() -> {
                    //   System.out.printf("fizzbuzz ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {

            try {
                question_1195.buzz(() -> {
                    // System.out.printf("buzz ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
}
