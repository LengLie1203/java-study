package com.lqw.feign.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LQW
 * @date 2021-04-27 15:10
 **/
@FeignClient(name ="jediproperty", url = "http://192.168.1.90:64914")
public interface TestFeign {

    @RequestMapping(value = "info")
    String fff();
}
