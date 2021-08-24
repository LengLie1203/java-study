package com.lqw.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 交替打印
 */
public class Question_1115_1 {

    private int n;

    public Question_1115_1(int n) {
        this.n = n;
    }

    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }


    public static void main(String[] args) {
        Question_1115_1 question_1115 = new Question_1115_1(10);

        new Thread(() -> {
            try {
                question_1115.foo(() -> {
                    System.out.printf("foo");
                    ;
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                question_1115.bar(() -> {
                    System.out.printf("bar");
                    ;
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
