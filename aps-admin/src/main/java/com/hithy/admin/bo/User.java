package com.hithy.admin.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class User {
    /**
     * 账号 email phone
     */
    @NotNull
    private String account;
    /**
     * 密码
     */
    @Length(min = 6, max = 16)
    private String password;
}
