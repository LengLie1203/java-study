package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-01 22:27
 **/
public class InterferingTask implements Runnable{
    private final int id;

    private static int val;

    public InterferingTask(int id) {
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
        for (int i = 0; i < 200; i++) {
            val++;
        }
        System.out.println(id + " "+
                Thread.currentThread().getName() + " " + val);
    }
}
