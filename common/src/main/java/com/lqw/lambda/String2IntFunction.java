package com.lqw.lambda;


import java.util.function.Function;

/**
 * @author luoquanwei
 */
public class String2IntFunction implements Function<String,Integer> {
    /**
     * Applies this function to the given argument.
     *
     * @param s the function argument
     * @return the function result
     */
    @Override
    public Integer apply(String s) {
        return Integer.parseInt(s);
    }
}
