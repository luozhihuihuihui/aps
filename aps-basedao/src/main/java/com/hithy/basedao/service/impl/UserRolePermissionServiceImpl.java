package com.hithy.basedao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hithy.basedao.dao.UserRolePermissionDao;
import com.hithy.basedao.entity.UserRolePermissionEntity;
import com.hithy.basedao.service.UserRolePermissionService;
import org.springframework.stereotype.Service;


@Service("userRolePermissionService")
public class UserRolePermissionServiceImpl extends ServiceImpl<UserRolePermissionDao, UserRolePermissionEntity> implements UserRolePermissionService {



}
