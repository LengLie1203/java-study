package com.lqw.annotation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author LQW
 * @date 2021-03-31 22:18
 **/
@Aspect
@Component
public class ParamAspect {

    @Pointcut("@annotation(com.lqw.annotation.aspect.Param)")
//    @Pointcut("execution(@com.lqw.annotation.aspect.Param * *(..))")
    public void check() {

    }

    @After("check()")
    public void execute(JoinPoint joinPoint){
        System.out.println("execute()");
    }


}
