package com.hithy.admin.service;

import com.hithy.admin.bo.RegisterBo;
import com.hithy.common.ApsResponse;

public interface UserService {
    /**
     * 注册
     * @param registerBo
     * @return
     */
    ApsResponse register(RegisterBo registerBo);

    /**
     * 验证码
     * @param email
     * @return
     */
    ApsResponse sendCode(String email);
}
