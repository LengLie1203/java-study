package com.lqw.concurrent;

/**
 * @author LQW
 * @date 2021-04-08 22:50
 **/
// concurrent/SharedConstructorArgument.java

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.*;

interface SharedArg {
    int get();
}

class Unsafe implements SharedArg {
    private int i = 0;

    @Override
    public int get() {
        return i++;
    }
}

class Safe implements SharedArg {
    private static AtomicInteger counter = new AtomicInteger();

    @Override
    public int get() {
        return counter.getAndIncrement();
    }
}

class SharedUser implements HasID {
    private final int id;

    SharedUser(SharedArg sa) {
        id = sa.get();
    }

    @Override
    public int getID() {
        return id;
    }
}

class SyncConstructor implements HasID{

    private int id;

    private static Object object=new Object();

    public SyncConstructor(SharedArg sa) {
        synchronized (object){
            id=sa.get();
        }
    }

    @Override
    public int getID() {
        return id;
    }
}

class SyncFactory implements HasID{
    private final int id;

    private SyncFactory(SharedArg sa) {
        this.id=sa.get();
    }

    @Override
    public int getID() {
        return id;
    }

    public static synchronized SyncFactory create(SharedArg sa){
        return new SyncFactory(sa);
    }
}

public class SharedConstructorArgument {
    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        IDChecker.test(() -> new SharedUser(unsafe));

        Safe safe = new Safe();
        IDChecker.test(() -> new SharedUser(safe));

        Unsafe unsafe2 = new Unsafe();
        IDChecker.test(() -> new SyncConstructor(unsafe2));

        Unsafe unsafe3 = new Unsafe();
        IDChecker.test(()->SyncFactory.create( unsafe3));
    }
}

