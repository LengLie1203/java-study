package com.lqw.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author LQW
 * @date 2021-04-01 21:57
 **/
public class Nap {

    public Nap(double i) {

        try {
            TimeUnit.MILLISECONDS.sleep((int) (i*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Nap(double i,String msg) {
        this(i);

        System.out.println(msg);
    }
}
