package com.lqw.concurrent;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author LQW
 * @date 2021-04-02 11:17
 **/
public class QuittableTask implements Runnable{
    private final int id;

    private AtomicBoolean running=new AtomicBoolean(true);

    public QuittableTask(int id) {
        this.id = id;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (running.get()){
            new Nap(0.1);
        }

        System.out.print(id+" ");
    }

    public void quit(){
//        System.out.println("quit()");
        running.set(false);
    }


    public static void main(String[] args) {
        QuittableTask task=new QuittableTask(1);

        new Thread(task).start();

        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.quit();
    }
}
