package com.lqw.onjava8;

import com.alibaba.ttl.TtlCopier;

import java.util.function.Consumer;
import java.util.function.IntFunction;

public class Lamda {

    public static void staticM() {

    }

    public void mm() {
        System.out.println(this);
    }

    public void mm2(Lamda lamda) {
    }

    public void  mm3(int i){
        System.out.println(i);
    }


    public int  mm4(int i){
        System.out.println(i);
        return i*2;
    }
    static class StaticC {
        public void mm() {
        }

        ;
    }


    public void specMm() {
    }

    public static void main(String[] args) {
        Runnable runnable = Lamda::staticM;

//        Runnable runnable1=new Lamda()::mm;

        InfParent infParent = Lamda::mm;
        Lamda lamda = new Lamda();
        System.out.println(lamda);
        infParent.useParent(lamda);

//        InfParent runnable1 = Lamda::mm2;

        Consumer<Integer> mm3 = lamda::mm3;

        mm3.accept(4);
        IntFunction<Integer> mm4 = lamda::mm4;
        Integer apply = mm4.apply(4);
        System.out.println(apply);
    }
}

interface InfParent {

    void useParent(Lamda lamda);
}