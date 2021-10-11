package com.hithy.auth.exception;

/**
 * 自定义401无权限异常(ApsUnauthorizedException)
 */
public class ApsUnauthorizedException extends RuntimeException {

    public ApsUnauthorizedException(String msg){
        super(msg);
    }

    public ApsUnauthorizedException() {
        super();
    }
}
