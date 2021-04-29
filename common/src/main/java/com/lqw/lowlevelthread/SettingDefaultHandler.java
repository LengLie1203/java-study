package com.lqw.lowlevelthread;

/**
 * @author LQW
 * @date 2021-04-10 16:53
 **/
// lowlevel/SettingDefaultHandler.java

import java.util.concurrent.*;

public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler());
        ExecutorService es =
                Executors.newCachedThreadPool();
        es.execute(new ExceptionThread());
        es.shutdown();
    }
}
/* Output:
caught java.lang.RuntimeException
*/
