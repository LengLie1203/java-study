package com.lqw;

/**
 * @author LQW
 * @date 2021-04-01 13:27
 **/
public class Timer {
    private long start;
    public Timer() {
        start=System.currentTimeMillis();
    }


    public long duration(){
        return System.currentTimeMillis()-start;
    }

    public static  long duration(Runnable runnable){
        long s=System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis()-s;
    }

}
