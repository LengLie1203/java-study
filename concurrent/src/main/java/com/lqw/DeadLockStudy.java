package com.lqw;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockStudy {

    private Lock left = new ReentrantLock(false);

    private Lock right = new ReentrantLock(false);

    public void leftRight() {
        left.lock();
        right.lock();
        System.out.println("do left right ");


        left.unlock();
        right.unlock();

    }

    public void sLeftRight() {
        synchronized (left){
            synchronized (right){

                    System.out.println("do left right ");

            }
        }

    }

    public void rightLeft() {
        right.lock();
        left.lock();
        System.out.println("do right left ");
        right.unlock();
        left.unlock();
    }

    public void sRightLeft() {
        synchronized (right) {
            synchronized (left) {
                System.out.println("do right left ");
            }
        }
    }


    public static void main(String[] args) {
        DeadLockStudy deadLockStudy = new DeadLockStudy();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            while (true) {
                deadLockStudy.sLeftRight();
            }
        });

        executorService.execute(() -> {
            while (true) {
                deadLockStudy.sRightLeft();
            }
        });

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}