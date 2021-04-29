package com.lqw.annotation.aspect;

import org.springframework.stereotype.Component;

/**
 * @author LQW
 * @date 2021-03-31 22:39
 **/
@Component
public class ParamTest {

    public static void main(String[] args) {
        ParamAspect aspect = new ParamAspect();
        ParamTest paramTest = new ParamTest();

        paramTest.test();
    }

    public Object test() {

       @Param String param="99999";
        System.out.println("test()");

        return null;
    }
}
