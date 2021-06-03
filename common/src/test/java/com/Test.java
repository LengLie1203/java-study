package com;

import java.util.Scanner;

/**
 * @author LQW
 * @date 2021-04-25 22:22
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        String str=null;
        try{
            System.out.println("step 1");
            System.out.println(str.length());
            System.out.println("step 2");
        }catch (Exception e){
            System.out.println("step 3");
//           int f= 1/0;
            System.out.println("step 4");
        }finally {
            System.out.println("step 5");
        }

        String num="8";
        int n=num.charAt(0);
        System.out.println(n-48);
    }
}
