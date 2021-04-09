package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-07 09:10
 **/
// concurrent/Batter.java

import org.apache.poi.ss.formula.functions.Na;

import java.util.concurrent.*;

public class Batter {
    static class Eggs {
    }

    static class Milk {
    }

    static class Sugar {
    }

    static class Flour {
    }

    public static  <T> T prepare(T ingredient){
        new Nap(0.1);
        return ingredient;
    }

    public static  <T> CompletableFuture<T> prep(T ingredient){
        new Nap(0.1);
        return CompletableFuture.completedFuture(ingredient)
                .thenApply(Batter::prepare);
    }

    public static CompletableFuture<Batter> mix(){
        CompletableFuture<Eggs> eggs = prep(new Eggs());
        CompletableFuture<Milk> milk = prep(new Milk());
        CompletableFuture<Sugar> sugar = prep(new Sugar());
        CompletableFuture<Flour> flour = prep(new Flour());
        CompletableFuture
                .allOf(eggs, milk, sugar, flour)
                .join();
        new Nap(0.1); // Mixing time
        return CompletableFuture.completedFuture(new Batter());
    }
}

