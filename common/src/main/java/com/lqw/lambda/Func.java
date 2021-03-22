package com.lqw.lambda;


import java.util.function.Consumer;
import java.util.function.Function;

public class Func {
    static int c = 5;
    Function<Integer, Integer> factorial = null;

    public Func() {
        factorial = i -> i == 1 ? 1 : factorial.apply(i - 1);
    }
    public static void main(String[] args) {
        Integer a = 10, b = 20;c++;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(a + b + c++);
            }
        };
        runnable.run();

        Function<String ,Function<String,String>> fun = aa -> bb -> aa+bb;
//        Function<Integer, Integer> factorial = null;
//        factorial = i -> i == 1 ? 1 : factorial.apply(i - 1);

    }

}
