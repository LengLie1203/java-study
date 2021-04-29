package com.lqw.lowlevelthread;

import com.lqw.concurrent.Nap;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LQW
 * @date 2021-04-13 18:55
 **/
class DualSynch {
     ConcurrentLinkedQueue<String> trace = new ConcurrentLinkedQueue<>();

    private Object syncObject = new Object();

    synchronized void f(boolean nap) {
        for (int i = 0; i < 5; i++) {
            trace.add("f() " + i);
            if (nap) {
                new Nap(0.01);
            }
        }
    }

    void g(boolean nap) {

        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                trace.add("g() " + i);
                if (nap) {
                    new Nap(0.01);
                }
            }
        }
    }
}

public class SyncOnObject {

    public static void test(boolean fNap,boolean gNap){
        DualSynch dualSynch=new DualSynch();
        List<CompletableFuture<Void>> futures = Stream.<Runnable>of(() -> dualSynch.f(fNap), () -> dualSynch.g(gNap))
                .map(CompletableFuture::runAsync).collect(Collectors.toList());

        futures.forEach(CompletableFuture::join);

        dualSynch.trace.forEach(System.out::println);
    }

    public static void main(String[] args) {
        test(true,false);
        System.out.println("------------------");
        test(false,true);
    }
}
