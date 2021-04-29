package com.lqw.lowlevelthread;

import com.lqw.concurrent.Nap;

/**
 * @author LQW
 * @date 2021-04-12 13:46
 **/
// lowlevel/SynchronizedEvenProducer.java
// Simplifying mutexes with the synchronized keyword

public class SynchronizedEvenProducer extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        new Nap(0.01); // Cause failure faster
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenProducer());
    }
}
/* Output:
No odd numbers discovered
*/

