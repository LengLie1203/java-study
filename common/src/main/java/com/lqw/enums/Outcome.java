package com.lqw.enums;

import com.google.common.collect.Lists;

import java.util.stream.Collectors;

/**
 * @author LQW
 * @date 2021-03-30 13:45
 **/
public enum Outcome {
    WIN, LOSE, DRAW;

    public static void main(String[] args) {
        Outcome[] enumConstants = Outcome.class.getEnumConstants();
        StringBuilder collect = Lists.newArrayList(enumConstants).stream().collect(StringBuilder::new,
                (s1,e)->s1.append(",").append(e), StringBuilder::append);
Collectors.joining();
        System.out.println(collect.toString());
    }
}
