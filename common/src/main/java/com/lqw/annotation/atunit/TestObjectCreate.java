package com.lqw.annotation.atunit;

/**
 * @author LQW
 * @date 2021-03-31 15:13
 **/

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestObjectCreate {
}
