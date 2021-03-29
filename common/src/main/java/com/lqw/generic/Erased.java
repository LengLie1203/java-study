package com.lqw.generic;

/**
 * @author LQW
 * @date 2021-03-25 22:30
 **/
public class Erased<T> {
    private final int SIZE = 100;

    public void f(Object arg) {
        // error: illegal generic type for instanceof
//        if (arg instanceof T) {
//        }
//        // error: unexpected type
//        T var = new T();
//        // error: generic array creation
//        T[] array = new T[SIZE];
//        // warning: [unchecked] unchecked cast
//        T[] array2 = (T[]) new Object[SIZE];

    }
}
