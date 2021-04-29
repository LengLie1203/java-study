package com.lqw.controller.impl;

import com.lqw.bean.DataRequest;
import com.lqw.bean.DataResponse;
import com.lqw.controller.HelloController;
import com.lqw.feign.controller.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerImpl implements HelloController {

    @Autowired
    TestFeign testFeign;
    @Override
    public String sayHello() {
        return "hello"+testFeign.fff();
    }

    @Override
    public DataResponse<String> sayHelloToSome(DataRequest<String> request) {
        return DataResponse.success("hello! " +request.getParam());
    }
}
