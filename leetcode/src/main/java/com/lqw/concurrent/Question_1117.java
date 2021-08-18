package com.lqw.concurrent;

import java.util.concurrent.*;

/**
 * 1117. H2O 生成
 */
public class Question_1117 {


    private Semaphore hydrogen = new Semaphore(2);

    private Semaphore oxygen = new Semaphore(0);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        oxygen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hydrogen.release(2);
    }


}
