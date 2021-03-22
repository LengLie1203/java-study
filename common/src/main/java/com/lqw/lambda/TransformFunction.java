package com.lqw.lambda;

import java.util.function.Function;

class I {
    @Override
    public String toString() { return "I"; }
}

class O {
    private int i;

    public O(int i) {
        this.i = i;
    }

    @Override
    public String toString() { return "O"; }

    public int getI() {
        return i;
    }
}

public class TransformFunction {
    static Function<I,O> transform(Function<I,O> in) {
        return in.andThen(o -> {
            System.out.println(o+" "+o.getI());
            return new O(4);
        });
    }
    public static void main(String[] args) {
        Function<I,O> f2 = transform(i -> {
            System.out.println(i);
            return new O(2);
        });
        O o = f2.apply(new I());

        System.out.println(o.getI());
    }
}
