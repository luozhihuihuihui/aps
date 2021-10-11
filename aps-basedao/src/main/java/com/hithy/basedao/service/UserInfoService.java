package com.hithy.basedao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hithy.basedao.entity.UserInfoEntity;

/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-26 10:38:15
 */
public interface UserInfoService extends IService<UserInfoEntity> {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    UserInfoEntity getUserByAccount(String userName);
}

