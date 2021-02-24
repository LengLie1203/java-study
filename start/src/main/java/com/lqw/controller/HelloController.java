package com.lqw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("hello")
public interface HelloController {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String sayHello();
}
