package com.hithy.admin.service;

import com.hithy.admin.bo.RoleBo;
import com.hithy.common.ApsResponse;

public interface RoleService {
    /**
     * 新增角色
     * @param roleBo
     * @return
     */
    ApsResponse add(RoleBo roleBo);
}
