package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-06 21:37
 **/
// concurrent/DualCompletableOperations.java
import com.lqw.Timer;

import java.util.concurrent.*;
import java.util.function.Consumer;

import static com.lqw.concurrent.CompletableUtilities.showr;
import static com.lqw.concurrent.CompletableUtilities.voidr;

public class DualCompletableOperations {
    static CompletableFuture<Workable> cfA, cfB;

    static void init() {
        cfA = Workable.make("A", 0.15);
        cfB = Workable.make("B", 0.10); // Always wins
    }

    static void join() {
        cfA.join();
        cfB.join();
        System.out.println("*****************");
    }

    public static void main(String[] args) {
//        source();
        exam();
    }
    private static void exam(){
        accept(()->{
            cfA.runAfterEitherAsync(cfB,()-> System.out.println("runAfterEitherAsync "));
        });

        accept(()->{
            cfA.runAfterBothAsync(cfB,()-> System.out.println("runAfterBoth"));
        });
    }

    private static void accept(Runnable runnable){
        init();
        Timer timer=new Timer();
        runnable.run();
        join();
        System.out.println(timer.duration());
    }

    private static void source() {
        init();
        voidr(cfA.runAfterEitherAsync(cfB, () ->
                System.out.println("runAfterEither")));
        join();

        init();
        voidr(cfA.runAfterBothAsync(cfB, () ->
                System.out.println("runAfterBoth")));
        join();

        init();
        showr(cfA.applyToEitherAsync(cfB, w -> {
            System.out.println("applyToEither: " + w);
            return w;
        }));
        join();

        init();
        voidr(cfA.acceptEitherAsync(cfB, w -> {
            System.out.println("acceptEither: " + w);
        }));
        join();

        init();
        voidr(cfA.thenAcceptBothAsync(cfB, (w1, w2) -> {
            System.out.println("thenAcceptBoth: "
                    + w1 + ", " + w2);
        }));
        join();

        init();
        showr(cfA.thenCombineAsync(cfB, (w1, w2) -> {
            System.out.println("thenCombine: "
                    + w1 + ", " + w2);
            return w1;
        }));
        join();

        init();
        CompletableFuture<Workable>
                cfC = Workable.make("C", 0.08),
                cfD = Workable.make("D", 0.09);
        CompletableFuture.anyOf(cfA, cfB, cfC, cfD)
                .thenRunAsync(() ->
                        System.out.println("anyOf"));
        join();

        init();
        cfC = Workable.make("C", 0.08);
        cfD = Workable.make("D", 0.09);
        CompletableFuture.allOf(cfA, cfB, cfC, cfD)
                .thenRunAsync(() ->
                        System.out.println("allOf"));
        join();
    }
}

