package com.lqw.lowlevelthread;

/**
 * @author LQW
 * @date 2021-04-12 09:46
 **/
public class EvenProducer  extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue; // [1]
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String[] args) {
        EvenChecker.test(new EvenProducer());


    }
}
