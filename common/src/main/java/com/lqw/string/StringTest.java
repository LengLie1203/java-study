package com.lqw.string;

import java.util.Formatter;

/**
 * @author LQW
 * @date 2021-03-22 22:08
 **/
public class StringTest {

    public static void main(String[] args) {
        Formatter formatter=new Formatter(System.out);

        System.out.println("+911".matches("(-|\\+)?\\d+"));
    }
}
