package com.lqw;

/**
 * @author LQW
 * @date 2021-06-09 15:51
 **/
public interface Computable<K,V> {

    V compute(K key) throws InterruptedException;
}
