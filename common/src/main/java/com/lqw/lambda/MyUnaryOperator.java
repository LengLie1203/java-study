package com.lqw.lambda;

import java.util.function.UnaryOperator;

public class MyUnaryOperator implements UnaryOperator<Integer> {

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public Integer apply(Integer integer) {
        if (integer<10){
            return ++integer;
        }
        return null;
    }
}
