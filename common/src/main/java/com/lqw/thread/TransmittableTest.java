package com.lqw.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableTest {


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalUtil1.set("ThreadLocalUtil1");
        ThreadLocalUtil2.set("ThreadLocalUtil2");
        ThreadLocalUtil3.set("ThreadLocalUtil3");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> System.out.println(ThreadLocalUtil1.get()));
        executorService.execute(() -> System.out.println(ThreadLocalUtil2.get()));
        Thread.sleep(1111);
        ThreadLocalUtil2.set("ThreadLocalUtil2222222");
        executorService.execute(() -> System.out.println(ThreadLocalUtil2.get()));
        executorService.execute(TtlRunnable.get(() -> System.out.println(ThreadLocalUtil3.get())));
        Thread.sleep(1111);
        ThreadLocalUtil3.set("ThreadLocalUtil3333333");
        executorService.execute(TtlRunnable.get(() -> System.out.println(ThreadLocalUtil3.get())));

    }

    private static class ThreadLocalUtil3 {
        private static final ThreadLocal<String> local = new TransmittableThreadLocal<>();

        public static void set(String ss) {
            local.set(ss);
        }

        public static String get() {
            return local.get();
        }
    }

    private static class ThreadLocalUtil1 {

        private static final ThreadLocal<String> local = new ThreadLocal<>();


        public static void set(String ss) {
            local.set(ss);
        }
        public static String get() {
            return local.get();
        }

    }

    private static class ThreadLocalUtil2 {

        private static final ThreadLocal<String> local = new InheritableThreadLocal<>();


        public static void set(String ss) {
            local.set(ss);
        }
        public static String get() {
            return local.get();
        }

    }

}
