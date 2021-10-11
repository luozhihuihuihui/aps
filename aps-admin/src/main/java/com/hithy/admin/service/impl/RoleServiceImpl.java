package com.hithy.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hithy.admin.bo.RoleBo;
import com.hithy.admin.service.RoleService;
import com.hithy.auth.util.UserUtil;
import com.hithy.basedao.dao.UserRoleDao;
import com.hithy.basedao.entity.UserRoleEntity;
import com.hithy.basedao.entity.UserRolePermissionEntity;
import com.hithy.basedao.service.UserRolePermissionService;
import com.hithy.common.ApsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserRolePermissionService userRolePermissionService;

    @Transactional
    @Override
    public ApsResponse add(RoleBo roleBo) {
        String roleName = roleBo.getRoleName();
        if (existRoleName(roleName)) {
            return ApsResponse.error("role name exist!");
        }
        //新增role
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleName(roleName);
        userRoleDao.insert(userRoleEntity);
        //role绑定permission
        if (!ObjectUtils.isEmpty(roleBo.getPermissionId())) {
            List<UserRolePermissionEntity> userRolePermissionEnties = new ArrayList<>();
            for (int permissionId : roleBo.getPermissionId()) {
                UserRolePermissionEntity userRolePermissionEntity = new UserRolePermissionEntity();
                userRolePermissionEntity.setRoleId(userRoleEntity.getId());
                userRolePermissionEntity.setPermissionId(permissionId);
                userRolePermissionEnties.add(userRolePermissionEntity);
            }
            boolean add = userRolePermissionService.saveBatch(userRolePermissionEnties);
            if (!add) {
                ApsResponse.error("add role permission error!");
            }
        }
        return ApsResponse.ok();
    }

    private boolean existRoleName(String name) {
        UserRoleEntity userRoleEntity = userRoleDao.selectOne(new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getRoleName, name).last("limit 1"));
        return !ObjectUtils.isEmpty(userRoleEntity);

    }
}
