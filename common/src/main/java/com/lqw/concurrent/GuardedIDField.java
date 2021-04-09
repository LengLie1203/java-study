package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-08 22:46
 **/
// concurrent/GuardedIDField.java

import java.util.concurrent.atomic.*;

public class GuardedIDField implements HasID {

    private static AtomicInteger counter = new AtomicInteger();

    private int id = counter.getAndIncrement();

    @Override
    public int getID() {
        return id;
    }

    public static void main(String[] args) {
        IDChecker.test(GuardedIDField::new);
    }
}

