package com.lqw.concurrent;

import com.lqw.Timer;

import java.util.concurrent.CompletableFuture;


/**
 * @author LQW
 * @date 2021-04-06 13:44
 **/
public class CompletableApplyChained {

    public static void main(String[] args) {
        Timer timer=new Timer();

        CompletableFuture<Machina> future = CompletableFuture.completedFuture(new Machina(0))
                .thenApply(Machina::work)
                .thenApply(Machina::work)
                .thenApply(Machina::work)
                .thenApply(Machina::work);

        System.out.println(timer.duration());
    }
}
