package com.lqw.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LQW
 * @date 2021-03-26 09:47
 **/
public class ListType {

    public static void main(String[] args) {
        List<P> list=new ArrayList<>();

        list.add(new P());
        list.add(new CC());


        List<? extends P> plist=new ArrayList<CC>();

        // can't compile
//        list=new ArrayList<C>();

        P[] p=new CC[5];

        p[0]=new CC();
        p[1]=new P();
    }
}

class CC extends P{}

class P{}