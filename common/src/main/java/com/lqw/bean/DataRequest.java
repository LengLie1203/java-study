package com.lqw.bean;

public class DataRequest<T> {

    private T param;


    public DataRequest() {
    }

    public DataRequest(T param) {
        this.param = param;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
