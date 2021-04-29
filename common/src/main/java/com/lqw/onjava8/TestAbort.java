package com.lqw.onjava8;

import com.lqw.concurrent.Nap;

/**
 * @author LQW
 * @date 2021-04-12 09:38
 **/
// lowlevel/TestAbort.java

public class TestAbort {
    public static void main(String[] args) {
        new TimedAbort(1);
        System.out.println("Napping for 4");
        new Nap(4);
    }
}
/* Output:
Napping for 4
TimedAbort 1.0
*/

