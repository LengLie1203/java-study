package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-07 13:35
 **/
// concurrent/Breakable.java
import java.util.concurrent.*;
public class Breakable {
    String id;
    private int failcount;

    public Breakable(String id, int failcount) {
        this.id = id;
        this.failcount = failcount;
    }

    @Override
    public String toString() {
        return "Breakable_" + id + " [" + failcount + "]";
    }

    public static Breakable work(Breakable b) {
        if (--b.failcount == 0) {
            System.out.println(
                    "Throwing Exception for " + b.id + ""
            );
            throw new RuntimeException(
                    "Breakable_" + b.id + " failed"
            );
        }
        System.out.println(b);
        return b;
    }
}

