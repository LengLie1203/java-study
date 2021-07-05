package com.lqw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author LQW
 * @date 2021-05-15 10:24
 **/
public class MyThreadStudy {


    public static void main(String[] args) {

        Obj obj = new Obj("obj");
        Obj obj1 =new Obj("obj1");

        new Thread(obj::use).start();
        new Thread(obj::use).start();
        new Thread(obj1::use).start();
    }

    void holdLock() {
        Obj obj = new Obj();

        System.out.println(Thread.holdsLock(obj));

        obj.use();

    }

    void synctest(){
        new Thread(MyThreadStudy::work).start();

        new Thread(MyThreadStudy::work).start();
    }

    public static synchronized void work(){
        System.out.println("Work hard...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    static class Obj {

        private String name;

        public Obj() {
        }

        public Obj(String name) {
            this.name = name;
        }

        synchronized void use() {
            try {

                System.out.println(Thread.holdsLock(this));
                System.out.println(name);
                Thread.sleep(2 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
