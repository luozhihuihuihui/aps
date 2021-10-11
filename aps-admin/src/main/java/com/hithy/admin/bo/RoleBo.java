package com.hithy.admin.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleBo {
    /**
     * 角色名称
     */
    @NotNull
    private String roleName;
    /**
     * 权限ID
     */
    private List<Integer> permissionId;
}
