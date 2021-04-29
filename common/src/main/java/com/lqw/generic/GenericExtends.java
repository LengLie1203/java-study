package com.lqw.generic;

import java.util.Iterator;
import java.util.List;

/**
 * @author LQW
 * @date 2021-04-02 15:21
 **/
public class GenericExtends {


    public static void main(String[] args) {

        GP<String> stringGP=new GS<String,Integer>();

        List<String> next = stringGP.next();
    }
}

class GP<T> implements Iterator<List<T>> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public List<T> next() {
        return null;
    }
}

class GS<T,R> extends GP<T>{}