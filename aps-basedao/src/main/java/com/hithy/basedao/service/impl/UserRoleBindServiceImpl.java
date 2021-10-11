package com.hithy.basedao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hithy.basedao.dao.UserRoleBindDao;
import com.hithy.basedao.entity.UserRoleBindEntity;
import com.hithy.basedao.service.UserRoleBindService;
import org.springframework.stereotype.Service;


@Service("userRoleBindService")
public class UserRoleBindServiceImpl extends ServiceImpl<UserRoleBindDao, UserRoleBindEntity> implements UserRoleBindService {

}
