package com.lqw;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.NotThreadSafe;

/**
 * @author LQW
 * @date 2021-05-08 14:40
 **/
@NotThreadSafe
public class Sequence {

    @GuardedBy("this")
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}
