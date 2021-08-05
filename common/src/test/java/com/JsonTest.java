package com;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class JsonTest {

    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        String data = JSON.toJSONString(map.get("data"));
        map.put("code",22);
        System.out.println("22".equals(map.get("code") ));


    }
}
