package com.lqw.hashcodeequals;

/**
 * @author LQW
 * @date 2021-04-15 09:31
 **/
// equalshashcode/Prediction.java
// Predicting the weather
import java.util.*;
public class Prediction {
    private static Random rand = new Random(47);
    @Override
    public String toString() {
        return rand.nextBoolean() ?
                "Six more weeks of Winter!" : "Early Spring!";
    }
}

