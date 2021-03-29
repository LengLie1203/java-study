package com.lqw.generic;


import com.github.javafaker.App;
import com.google.common.collect.Lists;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LQW
 * @date 2021-03-26 20:53
 **/
public class CompilerIntelligence<T extends String> {
    private T d;

    public static void main(String[] args) {
        List<ZhiWu> zhiWuList = new ArrayList<ZhiWu>();
        zhiWuList.add(new ZhiWu());
        zhiWuList.add(new Fruit());
        zhiWuList.add(new Orange());
        zhiWuList.add(new Apple());
        zhiWuList.add(new Jonathan());

        /**
         * f(⋅)是逆变（contravariant）的，当A≤B时有f(B)≤f(A)成立；
         * f(⋅)是协变（covariant）的，当A≤B时有成立f(A)≤f(B)成立；
         * f(⋅)是不变（invariant）的，当A≤B时上述两个式子均不成立，即f(A)与f(B)相互之间没有继承关系。
         *
         *
         */
        //java中数组是协变的，这个可以实现。因为数组能够得到足够多的类型信息
        Collection[] collections = new ArrayList[]{};


        // 这里无法编译通过，根本原因是范型是不变的，
        // 因为范型使用了类型擦除，在运行时无法知道具体类型是什么（这个就是因为java要兼容老代码）
//        zhiWuList=new ArrayList<Fruit>();

        //使用 ? extends T 实现协变,规定了容器持有类型的上限
        List<? extends ZhiWu> extendsList = new ArrayList<ZhiWu>();
        extendsList = new ArrayList<Fruit>();
        extendsList = new ArrayList<Apple>();
        extendsList = new ArrayList<Orange>();
        extendsList = new ArrayList<Jonathan>(Lists.newArrayList(new Jonathan()));

        //编译失败，从上面可以看到无法知道extendsList到底指定了什么参数类型
        //如果extendsList使用了Orange类型参数，下边必定编译失败。所以使用协变时不允许add
//        extendsList.add(new ZhiWu());

        //这里可以直接返回,因为指定了上界，类型必定是ZhiWu的子类
        ZhiWu zhiWu = extendsList.get(0);

        //使用 ? super T 实现逆变，规定了容器持有类型的下限
        List<? super Apple> superlist = superlist = new ArrayList<Apple>();
//        superlist = new ArrayList<Jonathan>();
//        superlist=new ArrayList<Orange>();
        superlist = new ArrayList<Fruit>();
        superlist = new ArrayList<ZhiWu>();

        //这里可以添加进去,因为规定了下界，所以superlist无论使用了什么参数类型，
        // 一定都是Apple的父类，那Apple的子类必定可以存入
        superlist.add(new Apple());
        superlist.add(new Jonathan());

        //这里不能直接返回具体类型，因为都是Apple的父类，无法确定具体父类，则使用Object
        Object object = superlist.get(0);
        //要使用强转，转成实际使用的类
        ((Apple) superlist.get(0)).name();

        ((ZhiWu) superlist.get(0)).name();

        superlist = new ArrayList<Fruit>();
        superlist.add(new Apple());
        superlist.add(new Jonathan());
        //这里就会报错，因为向下转型了
//        ((Jonathan) superlist.get(0)).name();


        /**
         * 无界通配符 ?, 和 List<Object>存在区别
         * List<Object> 表示可以存放任意数据的集合，是原生的list
         * List<?> 表示任意指定参数类型的集合，只是不确定具体是什么类型，不是原始的list
         */
        List<?> anyList = new ArrayList<>();
        anyList = new ArrayList<String>();
        anyList = new ArrayList<Object>(Lists.newArrayList("fff"));

        //使用通配符的list,由于无法确定参数类型，编译器安全期间不允许添加数据
//        anyList.add("");
        System.out.println(anyList.get(0));


        //原始list，可以存任意类型
        List<Object> objectList = new ArrayList<Object>();
        objectList.add("fff");
        objectList.add(2);

        System.out.println(objectList.get(0));
    }

}

class ZhiWu {
    void name() {
        System.out.println(this.getClass().getName());
    }
}

class Fruit extends ZhiWu {
}

class Orange extends Fruit {
}

class Apple extends Fruit {

}

class Jonathan extends Apple {
}