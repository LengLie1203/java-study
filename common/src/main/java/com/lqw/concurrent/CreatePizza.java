package com.lqw.concurrent;

import com.lqw.Timer;

import java.util.stream.IntStream;

/**
 * @author LQW
 * @date 2021-04-08 23:30
 **/
// concurrent/PizzaStreams.java
public class CreatePizza {
    static final int QUANTITY = 10;

    public static void main(String[] args) {
//        onePizza();

        stepPizza();
    }

    public static void onePizza() {
        Pizza za = new Pizza(0);
        System.out.println(Timer.duration(() -> {
            while (!za.complete()) {
                za.next();
            }
        }));
    }

    public static void parallelPizza(){
        Timer timer = new Timer();
        IntStream.range(0, QUANTITY)
                .mapToObj(Pizza::new)
                .parallel()//[1]
                .forEach(za -> {
                    while (!za.complete()) za.next();
                });
        System.out.println(timer.duration());
    }

    public static void stepPizza(){
        Timer timer = new Timer();
        IntStream.range(0, QUANTITY)
                .mapToObj(Pizza::new)
                .parallel()
                .map(Pizza::roll)
                .map(Pizza::sauce)
                .map(Pizza::cheese)
                .map(Pizza::toppings)
                .map(Pizza::bake)
                .map(Pizza::slice)
                .map(Pizza::box)
                .forEach(za -> System.out.println(za));
        System.out.println(timer.duration());
    }
}

