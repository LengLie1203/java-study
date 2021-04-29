package com.lqw.hashcodeequals;

/**
 * @author LQW
 * @date 2021-04-14 22:09
 **/
// equalshashcode/DefaultComparison.java
public class DefaultComparison {
    private int i, j, k;
    DefaultComparison(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public static void main(String[] args) {
        DefaultComparison
                a = new DefaultComparison(1, 2, 3),
                b = new DefaultComparison(1, 2, 3);
        System.out.println(a == a);
        System.out.println(a == b);
    }
}
/*
Output:
true
false
*/

