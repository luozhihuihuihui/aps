package com.hithy.basedao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hithy.basedao.dao.UserInfoDao;
import com.hithy.basedao.entity.UserInfoEntity;
import com.hithy.basedao.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoEntity getUserByAccount(String accountName) {
        UserInfoEntity userInfoEntity = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoEntity>()
                .eq(UserInfoEntity::getEmail, accountName).or().eq(UserInfoEntity::getPhone, accountName));
        return ObjectUtils.isEmpty(userInfoEntity) ? null : userInfoEntity;
    }
}
