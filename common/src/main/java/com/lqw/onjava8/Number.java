package com.lqw.onjava8;

public class Number {


    public static void main(String[] args) {
        int a = 0XFFF;
        int b = 0_7;
        int c = 0B111000;

        int d = 0XFF_FF_FF;
//
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toHexString(a));
//
//        System.out.println(Integer.toOctalString(b));
//        System.out.println(b);

        System.out.printf("%xaaaa%n", 9999);

        System.out.printf("bbb%n");

        System.out.println("ccccc%n");

        System.out.println(b);

        fori:
        for (int i = 0; i < 10; i++) {
            forj:
            for (int j = 0; j < 100; j++) {
                System.out.print(j + " ");
                if (j == 50) {
                    System.out.println("i:" + i + " j:" + j);
                    continue fori;
                } else if (j > 50) {
                    System.out.println("j>50");
                }
            }
        }
    }
}
