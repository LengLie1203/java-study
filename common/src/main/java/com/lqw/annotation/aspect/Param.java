package com.lqw.annotation.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LQW
 * @date 2021-03-31 22:16
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.LOCAL_VARIABLE,ElementType.METHOD})
public @interface Param {
}
