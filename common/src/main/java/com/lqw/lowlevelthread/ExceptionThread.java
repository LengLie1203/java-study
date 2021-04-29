package com.lqw.lowlevelthread;

/**
 * @author LQW
 * @date 2021-04-10 16:26
 **/
// lowlevel/ExceptionThread.java
// {ThrowsException}
import java.util.concurrent.*;

public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }
    public static void main(String[] args) {
        ExecutorService es =
                Executors.newCachedThreadPool();
        //这样不会抛出异常
        es.submit(new ExceptionThread());
        //下面这个写法会抛出
//        Future<?> submit = es.submit(new ExceptionThread());
//
//        try {
//            Object o = submit.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
        //这样也会抛出异常
        es.execute(new ExceptionThread());

        es.shutdown();
    }
}
/* Output:
___[ Error Output ]___
Exception in thread "pool-1-thread-1"
java.lang.RuntimeException
        at ExceptionThread.run(ExceptionThread.java:8)
        at java.util.concurrent.ThreadPoolExecutor.runW
orker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Work
er.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)
*/

