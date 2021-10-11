package com.hithy.admin.config;


import com.alibaba.fastjson.JSONObject;
import com.hithy.auth.exception.ApsUnauthorizedException;
import com.hithy.common.ApsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ApsResponse bizExceptionHandler(HttpServletRequest req, RuntimeException e){
        log.error("RuntimeException：{}",e.getMessage());
        e.printStackTrace();
        return ApsResponse.error(e.getMessage());
    }
    /**
     * 处理验证失败
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ApsUnauthorizedException.class)
    @ResponseBody
    public ApsResponse ApsUnauthorizedException(HttpServletRequest req, ApsUnauthorizedException e){
        log.warn(e.getMessage());
        return ApsResponse.error(e.getMessage());
    }

    /**
     * 处理参数异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ApsResponse MethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.warn(JSONObject.toJSONString(errors));
        return ApsResponse.error(JSONObject.toJSONString(errors));
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ApsResponse exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("NullPointerException：{}",e.getMessage());
        e.printStackTrace();
        return ApsResponse.error(e.getMessage());
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ApsResponse exceptionHandler(HttpServletRequest req, Exception e){
        log.error("Exception：{}",e.getMessage());
        e.printStackTrace();
        return ApsResponse.error(e.getMessage());
    }
}
