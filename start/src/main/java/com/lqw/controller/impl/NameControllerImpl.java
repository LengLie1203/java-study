package com.lqw.controller.impl;

import com.lqw.bean.DataResponse;
import com.lqw.controller.NameController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LQW
 * @date 2021-04-29 17:26
 **/
@RestController
public class NameControllerImpl implements NameController {

    @Override
    public DataResponse<String> getName(@PathVariable("name") String name) {
        int x= 1/0;
        return DataResponse.success(name);
    }
}
