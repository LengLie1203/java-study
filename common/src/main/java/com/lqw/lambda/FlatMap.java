package com.lqw.lambda;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

    public static void main(String[] args) {
//        Stream.of(1,2,3)
//                .flatMap(i->Stream.of("aa","bb","cc").map(c->c+i))
//                .forEach(System.out::println);
//
//
//        Stream.of(Lists.newArrayList(1,2,3),Lists.newArrayList(4,5,6))
//                .flatMap(Collection::stream).forEach(System.out::println);

        Optional<Integer> reduce = Stream.of(11,2,9,4,5).reduce((i,b) -> i>b?i:b);

        System.out.println(reduce.get());

        reduce.filter(i->i.equals(10)).ifPresent(System.out::print);
        reduce.map(Function.identity()).flatMap(i->Optional.ofNullable(i)).get();

        System.out.println(Optional.of(new Object()));

        Object[] objects = Stream.of("2", "3").toArray();

        System.out.println(objects[0]+" "+objects[1]);

        String[] strings = Stream.of("2", "3","4").toArray(i->{
            System.out.println(i);
            return new String[5];
        });
//        System.out.println(strings.length);

        Stream.of("").collect(Collectors.toList());

        Stream.of("").collect((Supplier<ArrayList>) ArrayList::new ,(list,i)->list.add(i),
                (left, right) -> left.addAll(right));

    }

    public static String[] ff(int i){
        return new String[i];
    }
}
