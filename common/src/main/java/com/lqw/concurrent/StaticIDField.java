package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-08 22:39
 **/
// concurrent/StaticIDField.java
interface HasID {
    public int getID();
}

public class StaticIDField implements HasID {
    private static int counter = 0;
    private int id = counter++;

    @Override
    public int getID() {
        return id;
    }

    public static void main(String[] args) {
        IDChecker.test(StaticIDField::new);
    }
}

