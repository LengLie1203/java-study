package com.lqw.concurrent;

import com.lqw.Timer;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @author LQW
 * @date 2021-04-07 09:55
 **/
public class Baked {

    static class Pan{}

    static Pan pan(Batter b){
        new Nap(0.1);
        return new Pan();
    }

    static Baked heat(Pan pan){
        new Nap(0.1);
        return new Baked();
    }

    static CompletableFuture<Baked> bake(CompletableFuture<Batter> cfb){
        return cfb.thenApplyAsync(Baked::pan).thenApplyAsync(Baked::heat);
    }

    public static Stream<CompletableFuture<Baked>> batch(){
        Timer timer=new Timer();
        CompletableFuture<Batter> batter = Batter.mix();
        System.out.println(timer.duration());
        return Stream.of(bake(batter), bake(batter), bake(batter), bake(batter));
    }

    public static void main(String[] args) {
        Timer timer=new Timer();

        batch();
        System.out.println(timer.duration());
    }
}
