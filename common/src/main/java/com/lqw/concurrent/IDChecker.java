package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-08 22:41
 **/
// concurrent/IDChecker.java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.concurrent.*;
import com.google.common.collect.Sets;
public class IDChecker {
    public static final int SIZE = 100_000;

    static class MakeObjects implements Supplier<List<Integer>> {
        private Supplier<HasID> gen;

        MakeObjects(Supplier<HasID> gen) {
            this.gen = gen;
        }

        @Override public List<Integer> get() {
            return Stream.generate(gen)
                    .limit(SIZE)
                    .map(HasID::getID)
                    .collect(Collectors.toList());
        }
    }

    public static void test(Supplier<HasID> gen) {
        CompletableFuture<List<Integer>>
                groupA = CompletableFuture.supplyAsync(new MakeObjects(gen)),
                groupB = CompletableFuture.supplyAsync(new MakeObjects(gen));

        groupA.thenAcceptBoth(groupB, (a, b) -> {
            System.out.println(
                    Sets.intersection(
                            Sets.newHashSet(a),
                            Sets.newHashSet(b)).size());
        }).join();
    }
}

