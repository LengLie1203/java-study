package com.lqw.concurrent;

/**
 * 交替打印
 */
public class Question_1115_2 {

    private int n;

    public Question_1115_2(int n) {
        this.n = n;
    }

    private volatile boolean foo =true;

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!foo){
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            foo=false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (foo){
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo=true;
        }
    }


    public static void main(String[] args) {
        Question_1115_2 question_1115 = new Question_1115_2(10);

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
