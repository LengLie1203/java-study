package com.lqw.typeinfo;

import com.lqw.init.Init;
import com.lqw.init.InitP;

import java.util.Arrays;

/**
 * @author LQW
 * @date 2021-03-23 09:20
 **/
public class TypeInfo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {


        Class<? extends Number> integerClass = int.class;


        Class<Init> ftClass = Init.class;
        // Produces exact type:
        Init fancyToy = ftClass.newInstance();
        Class<? super Init> up = ftClass.getSuperclass();

         // This won't compile:
        // Only produces Object:
        Object obj = up.newInstance();
        //
        Class<? extends InitP> initPClass = Init.class;

        Class<? extends Init> aClass = initPClass.asSubclass(Init.class);

        System.out.println(aClass);

        Class<Integer> integerClass1 = Integer.class;

        Class<Number> numberClass = Number.class;

        System.out.println(numberClass.isAssignableFrom(integerClass1));


        System.out.println(double.class.isInstance(Integer.parseInt("3")));

        if (Integer.valueOf("3") instanceof Integer){

        }
    }
}
