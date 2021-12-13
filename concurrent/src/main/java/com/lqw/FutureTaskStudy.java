package com.lqw;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-06-03 19:32
 **/
public class FutureTaskStudy {

    private final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            if ((int) (Math.random() * 3) == 1) {
                throw new NullPointerException("HHHHH");
            }
            return "FutureTask";
        }
    });


    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }


    public String get() throws InterruptedException, ExecutionException {
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof NullPointerException){
                throw ((NullPointerException) e.getCause());
            }else {
                throw e;
            }

        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        memoizerStudy();


    }

    private static void memoizerStudy() {
        Memoizer<String,Integer> memoizer=new Memoizer<>((key)->{
            Thread.sleep(3000);
            return Integer.parseInt(key);
        });


        IntStream.range(0,8).mapToObj(i->new Thread(()->{
            for (int j = 0; j < i; j++) {
                try {
                    System.out.println(memoizer.compute(i + ""));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).forEach(t->t.start());
    }

    private static void simpleStudy() throws InterruptedException, ExecutionException {
        FutureTaskStudy study = new FutureTaskStudy();
        study.start();
        for (int i = 0; i < 4; i++) {

            System.out.println(study.futureTask.get());
        }
        System.out.println(study.get());
    }
}
