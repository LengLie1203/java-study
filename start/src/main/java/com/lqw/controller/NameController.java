package com.lqw.controller;

import com.lqw.bean.DataResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LQW
 * @date 2021-04-29 17:20
 **/
@RequestMapping(value = "name")
public interface NameController {

    @RequestMapping(value = "/getname/{name}",method = RequestMethod.GET )
    DataResponse<String> getName(String name);
}
