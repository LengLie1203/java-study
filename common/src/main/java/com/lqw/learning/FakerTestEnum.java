package com.lqw.learning;

public enum FakerTestEnum {

    HELLO("hello_n"),
    SORRY("sorry_n"),
    HAHA("haha_n"),
    FF("ff_n"),
    ;
    private String value;
    FakerTestEnum() {
    }

    FakerTestEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
