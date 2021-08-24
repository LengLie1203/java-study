package com.lqw.concurrent;

/**
 * 按序打印
 */
public class Question_1114 {

    public Question_1114() {

    }

    private int flag=1;

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this){
            while (flag!=1){
                wait();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag=2;
            notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this){
            while (flag!=2){
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag=3;
            notifyAll();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this){
            while (flag!=2){
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            flag=3;
            notifyAll();
        }

    }
}
