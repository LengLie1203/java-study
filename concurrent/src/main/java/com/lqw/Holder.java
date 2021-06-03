package com.lqw;

/**
 * @author LQW
 * @date 2021-05-14 11:48
 **/
public class Holder {
    private  int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity(){
        if(n!=n){
            throw new AssertionError("");
        }
    }
}
