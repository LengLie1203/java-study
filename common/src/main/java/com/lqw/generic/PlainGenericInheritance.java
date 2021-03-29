package com.lqw.generic;

import java.util.Collections;

/**
 * @author LQW
 * @date 2021-03-29 09:37
 **/
class GenericSetter<T> { // Not self-bounded
    void set(T arg) {
        System.out.println("GenericSetter.set(Base)");
    }
}

class DerivedGS<T> extends GenericSetter<T> {
//    void set(Derived derived) {
//        System.out.println("DerivedGS.set(Derived)");
//    }


    @Override
    void set(T arg) {
        super.set(arg);
    }
}

public class PlainGenericInheritance {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS<Base> dgs = new DerivedGS();
        dgs.set(derived);
        dgs.set(base); // Overloaded, not overridden!


//        dgs.set();
    }
}
