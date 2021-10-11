package com.hithy.basedao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hithy.basedao.entity.UserInfoEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDto extends UserInfoEntity {
    /**
     * 角色
     */
    private List<UserRoleDto> roles;
    /**
     * 登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;
}
