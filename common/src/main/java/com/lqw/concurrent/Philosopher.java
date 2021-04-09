package com.lqw.concurrent;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 哲学家
 * @author LQW
 * @date 2021-04-08 21:32
 **/
public class Philosopher implements Runnable{

    private int seat;

    private StickHolder left;
    private StickHolder right;

    private List<StickHolder.ChopStick> chopStickList= Lists.newArrayList();

    public Philosopher(int seat, StickHolder left, StickHolder right) {
        this.seat = seat;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "P"+seat;
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
        while (true){
            chopStickList.add( right.pickup());
            chopStickList.add( left.pickup());
            System.out.println(this+" eat!");
            say();
            right.putDown();
            left.putDown();
            chopStickList.clear();
        }
    }

    public void say(){
        System.out.println(this +":I have "+chopStickList.stream().map(Object::toString).collect(Collectors.joining(",")));
    }
}
