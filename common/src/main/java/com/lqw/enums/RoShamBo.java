package com.lqw.enums;


/**
 * @author LQW
 * @date 2021-03-30 16:21
 **/
public class RoShamBo {
    public static <T extends Competitor<T>> void match(T a, T b) {
        System.out.println(
                a + " vs. " + b + ": " + a.compete(b));
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
        for (int i = 0; i < size; i++)
            match(Enums.random(rsbClass), Enums.random(rsbClass));
    }
}
