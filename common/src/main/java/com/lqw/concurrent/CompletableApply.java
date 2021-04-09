package com.lqw.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * @author LQW
 * @date 2021-04-02 13:46
 **/
public class CompletableApply {
    public static void main(String[] args) {
        CompletableFuture<Machina> cf =
                CompletableFuture.completedFuture(
                        new Machina(0));
        CompletableFuture<Machina> cf2 =
                cf.thenApply(Machina::work);
        CompletableFuture<Machina> cf3 =
                cf2.thenApply(Machina::work);
        CompletableFuture<Machina> cf4 =
                cf3.thenApply(Machina::work);
        CompletableFuture<Machina> cf5 =
                cf4.thenApply(Machina::work);

    }
}
