package com.hithy.auth.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hithy.auth.exception.ApsException;
import com.hithy.basedao.entity.UserInfoEntity;
import com.hithy.basedao.service.UserInfoService;
import com.hithy.common.constant.ApsConstant;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取当前登录用户工具类
 */
@Component
public class UserUtil {

    private final UserInfoService userInfoService;

    @Autowired
    public UserUtil(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 获取当前登录用户
     *
     * @param
     * @return com.wang.model.UserDto
     * @author wliduo[i@dolyw.com]
     * @date 2019/3/15 11:48
     */
    public UserInfoEntity getUser() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        String account = JwtUtil.getClaim(token, ApsConstant.JWT.ACCOUNT);
        UserInfoEntity userDto = userInfoService.getOne(new LambdaQueryWrapper<UserInfoEntity>().eq(UserInfoEntity::getId, account));
        // 用户是否存在
        if (userDto == null) {
            throw new ApsException("该帐号不存在(The account does not exist.)");
        }
        return userDto;
    }

    /**
     * 获取当前登录用户Id
     *
     * @param
     * @return com.wang.model.UserDto
     * @author wliduo[i@dolyw.com]
     * @date 2019/3/15 11:48
     */
    public long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户Token
     *
     * @param
     * @return com.wang.model.UserDto
     * @author wliduo[i@dolyw.com]
     * @date 2019/3/15 11:48
     */
    public String getToken() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    /**
     * 获取当前登录用户Account
     *
     * @param
     * @return com.wang.model.UserDto
     * @author wliduo[i@dolyw.com]
     * @date 2019/3/15 11:48
     */
    public String getAccount() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        // 解密获得Account
        return JwtUtil.getClaim(token, ApsConstant.JWT.ACCOUNT);
    }
}
