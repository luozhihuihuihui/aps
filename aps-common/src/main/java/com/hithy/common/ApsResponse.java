package com.hithy.common;

import com.hithy.common.constant.ApsHttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;

/**
 * ResponseBean
 *
 * @author dolyw.com
 * @date 2018/8/30 11:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApsResponse {
    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    private ApsHttpStatus apsHttpStatus;

    public ApsResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ApsResponse ok() {
        ApsResponse apsResponse = new ApsResponse();
        apsResponse.setApsHttpStatus(ApsHttpStatus.OK);
        return apsResponse;
    }

    public static ApsResponse ok(String msg) {
        ApsResponse apsResponse = new ApsResponse();
        apsResponse.setCode(HttpStatus.OK.value());
        apsResponse.setMsg(msg);
        return apsResponse;
    }

    public static ApsResponse error(String msg) {
        ApsResponse apsResponse = new ApsResponse();
        apsResponse.setCode(ApsHttpStatus.ERROR.code());
        apsResponse.setMsg(msg);
        return apsResponse;
    }

    public static ApsResponse error() {
        ApsResponse apsResponse = new ApsResponse();
        apsResponse.setApsHttpStatus(ApsHttpStatus.ERROR);
        return apsResponse;
    }

    public static ApsResponse unauthorized(){
        ApsResponse apsResponse = new ApsResponse();
        apsResponse.setApsHttpStatus(ApsHttpStatus.UNAUTHORIZED);
        return apsResponse;
    }

    public static ApsResponse unauthorized(String msg){
        ApsResponse apsResponse = new ApsResponse();
        apsResponse.setCode(ApsHttpStatus.UNAUTHORIZED.code());
        apsResponse.setMsg(msg);
        return apsResponse;
    }

    public void setApsHttpStatus(ApsHttpStatus apsHttpStatus) {
        this.code = apsHttpStatus.code();
        this.msg = apsHttpStatus.value();
    }

    public ApsResponse data(Object data) {
        this.data = data;
        return this;
    }
}
