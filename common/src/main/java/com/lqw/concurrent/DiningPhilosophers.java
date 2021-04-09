package com.lqw.concurrent;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @author LQW
 * @date 2021-04-08 22:03
 **/
public class DiningPhilosophers {

    private static StickHolder[] stickHolders;

    private static Philosopher[] philosophers;

    public DiningPhilosophers(int n) {
        stickHolders=new StickHolder[n];
        philosophers=new Philosopher[n];
        Arrays.setAll(stickHolders, StickHolder::new);
        Arrays.setAll(philosophers,i->new Philosopher(i,stickHolders[i],stickHolders[(i+1)%n]));

        Arrays.stream(philosophers).forEach(CompletableFuture::runAsync);
    }

    public static void main(String[] args) {

        new DiningPhilosophers(5);

        new Nap(3,"shutdown");
        Arrays.stream(philosophers).forEach(Philosopher::say);
    }
}
