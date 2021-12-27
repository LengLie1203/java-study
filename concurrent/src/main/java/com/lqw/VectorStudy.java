package com.lqw;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class VectorStudy {

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<String>(Lists.newArrayList("AAA", "CCC", "FFF"));

        Iterator<String> iterator = list.iterator();
        System.out.println(iterator.next());
        list.add("EEE");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
