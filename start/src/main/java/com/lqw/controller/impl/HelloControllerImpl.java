package com.lqw.controller.impl;

import com.lqw.controller.HelloController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerImpl implements HelloController {

    @Override
    public String sayHello() {
        return "hello";
    }
}
