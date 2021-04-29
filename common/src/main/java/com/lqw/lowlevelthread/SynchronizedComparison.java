package com.lqw.lowlevelthread;

import com.lqw.concurrent.Nap;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LQW
 * @date 2021-04-13 18:32
 **/
abstract class Guarded {
    protected AtomicLong callCount = new AtomicLong();

    abstract void method();

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                ": " + callCount.get();
    }
}

class SynchronizeMethod extends Guarded {
    @Override
    synchronized void method() {
        new Nap(0.01);
        callCount.incrementAndGet();
    }
}

class CriticalSection extends Guarded{
    @Override
    void method() {
        new Nap(0.01);
        synchronized (this){
            callCount.incrementAndGet();
        }
    }
}

class Caller implements Runnable{
    private Guarded g;
    private  AtomicLong successfulCalls=new AtomicLong();
    private AtomicBoolean stop=new AtomicBoolean(false);

    public Caller(Guarded g) {
        this.g = g;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        new Timer().schedule(new TimerTask(){

            /**
             * The action to be performed by this timer task.
             */
            @Override
            public void run() {
                stop.set(true);
            }
        },2500);

        while (!stop.get()){
            g.method();

            successfulCalls.getAndIncrement();
        }
        System.out.println(
                "-> " + successfulCalls.get());
    }
}

public class SynchronizedComparison {

    public static void test(Guarded g){
        List<CompletableFuture<Void>> futures = Stream.of(new Caller(g), new Caller(g), new Caller(g), new Caller(g))
                .map(CompletableFuture::runAsync).collect(Collectors.toList());

        futures.forEach(CompletableFuture::join);
        System.out.println(g);
    }


    public static void main(String[] args) {
        test(new CriticalSection());
        test(new SynchronizeMethod());
    }
}
