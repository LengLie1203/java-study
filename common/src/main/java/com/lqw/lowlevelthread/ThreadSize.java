package com.lqw.lowlevelthread;

/**
 * @author LQW
 * @date 2021-04-10 16:17
 **/
// lowlevel/ThreadSize.java
// {ExcludeFromGradle} Takes a long time or hangs
import com.lqw.concurrent.Nap;

import java.util.concurrent.*;

/**
 * 添加了JVM限制 -Xss1M -Xmx5M
 */
public class ThreadSize {
    static class Dummy extends Thread {
        @Override
        public void run() { new Nap(1); }
    }
    public static void main(String[] args) {
        ExecutorService exec =
                Executors.newCachedThreadPool();
        int count = 0;
        try {
            while(true) {
                exec.execute(new Dummy());
                count++;
            }
        } catch(Error e) {
            System.out.println(
                    e.getClass().getSimpleName() + ": " + count);
            System.exit(0);
        } finally {
            exec.shutdown();
        }
    }
}
