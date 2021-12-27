package com;

import com.alibaba.fastjson.JSON;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {

    public static void main(String[] args) {
String partten="是否违反解耦减肥王女士两地分居{0}，为富威尔232对方{1}水电费";
        String format = MessageFormat.format(partten, "FFFFF", "AAAAA");

        System.out.println(format);

        System.out.println(String.format(partten, "FFFFF", "AAAAA"));

    }
}
