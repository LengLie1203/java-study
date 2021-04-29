package com.lqw.hashcodeequals;

/**
 * @author LQW
 * @date 2021-04-15 09:31
 **/
// equalshashcode/Groundhog.java
// Looks plausible, but doesn't work as a HashMap key
public class Groundhog {
    protected int number;
    public Groundhog(int n) { number = n; }
    @Override
    public String toString() {
        return "Groundhog #" + number;
    }
}

