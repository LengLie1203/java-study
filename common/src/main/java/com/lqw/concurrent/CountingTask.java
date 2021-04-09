package com.lqw.concurrent;

import java.util.concurrent.Callable;

/**
 * @author LQW
 * @date 2021-04-02 09:21
 **/
public class CountingTask implements Callable<Integer> {
    private final int id;

    public CountingTask(int id) {
        this.id = id;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call()   {
        Integer val = 0;
        for(int i = 0; i < 100; i++)
            val++;
        System.out.println(id + " " +
                Thread.currentThread().getName() + " " + val);
        return val;
    }
}
