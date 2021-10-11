package com.hithy.basedao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hithy.basedao.dao.UserPermissionDao;
import com.hithy.basedao.entity.UserPermissionEntity;
import com.hithy.basedao.service.UserPermissionService;
import org.springframework.stereotype.Service;


@Service("userPermissionService")
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionDao, UserPermissionEntity> implements UserPermissionService {



}
