package com.lqw.onjava8;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayList<T>  extends ArrayList<T> {



    public Iterable<T> reversed(){
       return new Iterable<T>() {
           @Override
           public Iterator<T> iterator() {
               return new Iterator<T>() {
                   int current=size()-1;
                   @Override
                   public boolean hasNext() {
                       return false;
                   }

                   @Override
                   public T next() {
                       return null;
                   }
               };
           }
       };
    }


    public Iterator<T> fff(){
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
