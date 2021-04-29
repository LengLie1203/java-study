package com.lqw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * @author LQW
 * @date 2021-03-30 17:55
 **/
@Target(ElementType.METHOD)
@Inherited
public @interface UseCase {
    int id();

    String description() default "no description";
}
