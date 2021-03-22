package com.lqw.exception;

public enum FF implements Interface {

    AA("AA", "aa"),
    BB("BB", "bb"),
    ;
    private String code;

    private String value;

    FF(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static void main(String[] args) {

throw new RuntimeException("main");
    }
}
