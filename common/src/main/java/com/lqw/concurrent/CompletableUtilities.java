package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-06 20:58
 **/
import java.util.concurrent.*;

public class CompletableUtilities {
    // Get and show value stored in a CF:
    public static void showr(CompletableFuture<?> c) {
        try {
            System.out.println(c.get());
        } catch(InterruptedException
                | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    // For CF operations that have no value:
    public static void voidr(CompletableFuture<Void> c) {
        try {
            c.get(); // Returns void
        } catch(InterruptedException
                | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

