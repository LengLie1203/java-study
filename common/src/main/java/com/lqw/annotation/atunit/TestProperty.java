package com.lqw.annotation.atunit;

/**
 * @author LQW
 * @date 2021-03-31 15:14
 **/

import java.lang.annotation.*;

// Both fields and methods can be tagged as properties:
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestProperty {
}
