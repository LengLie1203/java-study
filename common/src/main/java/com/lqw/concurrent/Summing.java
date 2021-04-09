package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-01 09:36
 **/
// concurrent/Summing.java


import com.lqw.Timer;

import java.util.stream.*;
import java.util.function.*;

public class Summing {
    static void timeTest(String id, long checkValue, LongSupplier operation) {
        System.out.print(id + ": ");
        Timer timer=new Timer();
        long result = operation.getAsLong();
        if (result == checkValue)
            System.out.println(timer.duration() + "ms");
        else
            System.out.format("result: %d%n checkValue: %d%n", result, checkValue);
    }

    public static final int SZ = 100_000_000;
    // This even works:
    // public static final int SZ = 1_000_000_000;
    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2; // Gauss's formula

    public static void main(String[] args) {
        System.out.println(CHECK);
        timeTest("Sum Stream", CHECK, () ->
                LongStream.rangeClosed(0, SZ).sum());
        timeTest("Sum Stream Parallel", CHECK, () ->
                LongStream.rangeClosed(0, SZ).parallel().sum());
        timeTest("Sum Iterated", CHECK, () ->
                LongStream.iterate(0, i -> i + 1)
                        .limit(SZ + 1).sum());
        // Slower & runs out of memory above 1_000_000:
         timeTest("Sum Iterated Parallel", CHECK, () ->
           LongStream.iterate(0, i -> i + 1)
             .parallel()
             .limit(SZ+1).sum());
    }
}

