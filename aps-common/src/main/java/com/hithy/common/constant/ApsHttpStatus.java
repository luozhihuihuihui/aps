package com.hithy.common.constant;


public enum ApsHttpStatus {
    OK(200, "ok"),
    ERROR(500, "error"),
    UNAUTHORIZED(401, "unauthorized");

    private int code;
    private String value;

    ApsHttpStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public int code() {
        return this.code;
    }
}
