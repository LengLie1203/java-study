package com.lqw;

import java.util.concurrent.Semaphore;

/**
 * @author LQW
 * @date 2021-06-06 16:01
 **/
public class SemaphoreStudy {

    public static void main(String[] args) throws InterruptedException {

        study();
    }

    /**
     *
     * @throws InterruptedException
     */
    private static void study() throws InterruptedException {
        Semaphore semaphore=new Semaphore(1);

        semaphore.acquire();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println("FFFFFF");
                semaphore.acquire();
                System.out.println("SSSSSS");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        new Thread(()->{
            try {
                Thread.sleep(2000);
                semaphore.release();
                System.out.println("semaphore.release()");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
