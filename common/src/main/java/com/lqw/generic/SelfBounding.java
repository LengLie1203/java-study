package com.lqw.generic;

/**
 * @author LQW
 * @date 2021-03-29 09:05
 **/
class SelfBound<T extends SelfBound<T>>{
    T element;
    SelfBound<T> set(T arg) {
        element = arg;
        return this;
    }
    T get() { return element; }

    SelfBound<T> getThis(){return this;};
}

class A extends SelfBound<A>{
    @Override
    A getThis() {
        return this;
    }
}

class B extends SelfBound<A>{
    @Override
    SelfBound<A> set(A arg) {
        return super.set(arg);
    }

    @Override
    A  get() {
        return  new A1();
    }

    @Override
    B getThis() {
        return this;
    }
}

class A1 extends A{}
class C extends SelfBound<C> {
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}
class D {}
//Compile error
//class E extends SelfBound<D>{}

public class SelfBounding {
}
