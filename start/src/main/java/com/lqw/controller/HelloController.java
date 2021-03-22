package com.lqw.controller;

import com.lqw.bean.DataRequest;
import com.lqw.bean.DataResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("hello")
public interface HelloController {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String sayHello();


    @RequestMapping(value = "/sayHelloToSome",method = RequestMethod.POST)
    DataResponse<String> sayHelloToSome(@RequestBody DataRequest<String> request);
}
