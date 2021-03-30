package com.lqw.enums;

/**
 * @author LQW
 * @date 2021-03-30 16:21
 **/
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}