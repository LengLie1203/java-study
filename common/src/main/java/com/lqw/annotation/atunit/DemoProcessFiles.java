package com.lqw.annotation.atunit;

/**
 * @author LQW
 * @date 2021-03-31 15:21
 **/
public class DemoProcessFiles {
    public static void main(String[] args) {
        new ProcessFiles(file -> System.out.println(file),
                "java").start(args);
    }
}
