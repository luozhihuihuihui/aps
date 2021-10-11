package com.hithy.admin.bo;

import com.hithy.common.constant.ApsValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterBo {
    /**
     * 邮箱
     */
    @Email
    private String email;
    /**
     * 电话
     */
    @Pattern(regexp = ApsValidator.REGEX_MOBILE, message = "不是正确的手机号")
    private String phone;
    /**
     * 密码
     */
    @Length(min = 6,max = 16,message = "密码6-16位")
    private String password;
    /**
     * 注册类型
     */
    private String registerType;
    /**
     * 验证码
     */
    @NotNull
    private String code;
}
