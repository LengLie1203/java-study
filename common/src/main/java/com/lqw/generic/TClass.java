package com.lqw.generic;

import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author LQW
 * @date 2021-03-25 21:44
 **/
public class TClass<T> {

    private T data;

    public TClass(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TClass{" +
                "data=" + data.getClass().getName() +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) throws IOException {

        TClass<String> f=new TClass<>("fefef");
        f.setData("fff");

        System.out.println(f.getData());
    }
}
