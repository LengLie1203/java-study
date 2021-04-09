package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-06 21:34
 **/
// concurrent/Workable.java
import java.util.concurrent.*;

public class Workable{
    String id;
    final double duration;

    public Workable(String id, double duration) {
        this.id = id;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Workable[" + id + "]";
    }

    public static Workable work(Workable tt) {
        new Nap(tt.duration); // Seconds
        tt.id = tt.id + "W";
        System.out.println(tt);
        return tt;
    }

    public static CompletableFuture<Workable> make(String id, double duration) {
        return CompletableFuture
                .completedFuture(
                        new Workable(id, duration)
                )
                .thenApplyAsync(Workable::work);
    }
}