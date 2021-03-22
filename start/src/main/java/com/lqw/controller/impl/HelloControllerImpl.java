package com.lqw.controller.impl;

import com.lqw.bean.DataRequest;
import com.lqw.bean.DataResponse;
import com.lqw.controller.HelloController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerImpl implements HelloController {

    @Override
    public String sayHello() {
        return "hello";
    }

    @Override
    public DataResponse<String> sayHelloToSome(DataRequest<String> request) {
        return DataResponse.success("hello! " +request.getParam());
    }
}
