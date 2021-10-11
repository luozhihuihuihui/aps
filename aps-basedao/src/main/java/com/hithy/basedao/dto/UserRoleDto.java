package com.hithy.basedao.dto;

import com.hithy.basedao.entity.UserPermissionEntity;
import com.hithy.basedao.entity.UserRoleEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleDto extends UserRoleEntity {
    /**
     * 权限
     */
    private List<UserPermissionEntity> permissions;
}
