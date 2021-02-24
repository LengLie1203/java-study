package com;

import com.google.common.collect.Lists;
import com.lqw.lambda.MySupplier;
import com.lqw.lambda.String2IntFunction;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class String2IntFunctionTest {

    @Test
    void apply() {
        Stream.of("5").map(new String2IntFunction()).forEach(System.out::println);

        Stream.of("5").map((i)-> {
                return null;
        }).forEach(System.out::println);


        Stream.of("1").map(new String2IntFunction()::apply).forEach(System.out::println);


        Stream.of("2").map(new String2IntFunction()).forEach(System.out::println);


        Optional.ofNullable(null).orElseGet(MySupplier::new);

        Stream.of(Lists.newArrayList(),Lists.newArrayList()).flatMap(Collection::stream).forEach(i-> System.out.println(i));

        Stream.concat(Stream.of("1", "2"), Stream.of("3", "4")).collect(LinkedList::new,
                List::add, List::add).forEach(System.out::println);

        ;
    }

    @Test
    void iterateTest() {
//        Stream.iterate(1,new MyUnaryOperator()).forEach(i-> System.out.println(i));


//        Stream.iterate(1,i->i+1).forEach(i-> System.out.println(i));

        long l = Stream.iterate(1L, i -> i + 1).limit(3).reduce(Long::sum).get();
        System.out.println(l);

        Stream.iterate(1,i->i+1).skip(100).limit(3).forEach(System.out::println);
    }
}