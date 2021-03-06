package com.lqw.hashcodeequals;

/**
 * @author LQW
 * @date 2021-04-15 09:23
 **/
// equalshashcode/SubtypeEquality2.java

import java.util.*;

class Dog2 extends Animal {
    Dog2(String name, Size size) {
        super(name, size);
    }

    @Override
    public boolean equals(Object rval) {
        return rval instanceof Dog2 && super.equals(rval);
    }
}

class Pig2 extends Animal {
    Pig2(String name, Size size) {
        super(name, size);
    }

    @Override
    public boolean equals(Object rval) {
        return rval instanceof Pig2 &&
                super.equals(rval);
    }
}

public class SubtypeEquality2 {
    public static void main(String[] args) {
        Set<Animal> pets = new HashSet<>();
        pets.add(new Dog2("Ralph", Size.MEDIUM));
        pets.add(new Pig2("Ralph", Size.MEDIUM));
        pets.forEach(System.out::println);
    }
}
/*
Output:
Dog2[0]: Ralph MEDIUM a752aeee
Pig2[1]: Ralph MEDIUM a752aeee
*/

