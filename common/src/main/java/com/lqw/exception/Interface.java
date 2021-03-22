package com.lqw.exception;

public interface Interface {

    public String getCode();

    public String getValue();

    default Exception exception(){
        System.out.println(getCode()+" "+getValue());

        throw new NullPointerException(getCode()+" "+getValue());
    }
}
