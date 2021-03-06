package com.lqw.concurrent;

import com.lqw.Timer;

import java.util.concurrent.CompletableFuture;

/**
 * @author LQW
 * @date 2021-04-06 13:46
 **/
public class CompletableApplyAsync {


    public static void main(String[] args) {
        Timer timer=new Timer();

        CompletableFuture<Machina> future = CompletableFuture.completedFuture(new Machina(0))
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work);
        System.out.println(timer.duration());

        future.join();

        System.out.println(timer.duration());
    }
}
