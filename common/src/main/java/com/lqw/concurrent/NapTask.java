package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-01 21:54
 **/
public class NapTask implements Runnable {

    private final int id;

    public NapTask(int id) {
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
        new Nap(0.1);// Seconds
        System.out.println(this + " " +
                Thread.currentThread().getName());
    }


    @Override
    public String toString() {
        return "NapTask[" + id + "]";
    }
}
