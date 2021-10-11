package com.hithy.basedao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hithy.basedao.dao.UserRoleDao;
import com.hithy.basedao.entity.UserRoleEntity;
import com.hithy.basedao.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {



}
