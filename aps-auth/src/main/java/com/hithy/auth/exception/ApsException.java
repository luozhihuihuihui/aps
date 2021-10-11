package com.hithy.auth.exception;

/**
 * 自定义异常(ApsException)
 */
public class ApsException extends RuntimeException {

    public ApsException(String msg){
        super(msg);
    }

    public ApsException() {
        super();
    }
}
