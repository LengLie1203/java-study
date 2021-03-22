package com.lqw.exception;

public enum SS implements Interface {

    CC("CC", "cc"),
    DD("DD", "dd"),
    ;
    private String code;

    private String value;

    SS(String code, String value) {
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


}
