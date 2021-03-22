package com.lqw.thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorStudy {


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(5),new MyPolicy());
        System.out.println("当前线程："+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            int j=i;
            threadPoolExecutor.execute(()->{

                System.out.println(Thread.currentThread().getName()+":"+(j));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static class MyPolicy implements RejectedExecutionHandler {

        /**
         * Method that may be invoked by a {@link ThreadPoolExecutor} when
         * {@link ThreadPoolExecutor#execute execute} cannot accept a
         * task.  This may occur when no more threads or queue slots are
         * available because their bounds would be exceeded, or upon
         * shutdown of the Executor.
         *
         * <p>In the absence of other alternatives, the method may throw
         * an unchecked {@link RejectedExecutionException}, which will be
         * propagated to the caller of {@code execute}.
         *
         * @param r        the runnable task requested to be executed
         * @param executor the executor attempting to execute this task
         * @throws RejectedExecutionException if there is no remedy
         */
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("myPolicy thread:"+Thread.currentThread().getName());
        }
    }
}
