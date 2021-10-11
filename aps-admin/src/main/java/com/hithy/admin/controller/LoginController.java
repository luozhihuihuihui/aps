package com.hithy.admin.controller;


import com.hithy.admin.bo.User;
import com.hithy.auth.config.RedisConfig;
import com.hithy.auth.exception.ApsException;
import com.hithy.auth.util.RedisUtil;
import com.hithy.auth.util.UserUtil;
import com.hithy.common.ApsResponse;
import com.hithy.common.constant.ApsConstant;
import com.hithy.auth.exception.ApsUnauthorizedException;
import com.hithy.auth.util.AesCipherUtil;
import com.hithy.auth.util.JwtUtil;
import com.hithy.basedao.entity.UserInfoEntity;
import com.hithy.basedao.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Api(tags = "权限验证")
@RestController
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "登录", notes = "邮箱、手机登录", httpMethod = "POST")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    public ApsResponse login(HttpServletResponse httpServletResponse, @RequestBody @Valid User User) {
        String account = User.getAccount();
        String password = User.getPassword();
        UserInfoEntity userByName = userInfoService.getUserByAccount(account);
        if (userByName == null) {
            throw new ApsUnauthorizedException("该帐号不存在(The account does not exist.)");
        }
        String key = AesCipherUtil.deCrypto(userByName.getPassword());
        // 因为密码加密是以帐号+密码的形式进行加密的，所以解密后的对比是帐号+密码
        if (key.equals(password)) {
            // 清除可能存在的Shiro权限信息缓存
            if (redisUtil.exists(ApsConstant.Redis.PREFIX_APS_CACHE + userByName.getId())) {
                redisUtil.delKey(ApsConstant.Redis.PREFIX_APS_CACHE + userByName.getId());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            redisUtil.setObject(ApsConstant.Redis.PREFIX_APS_REFRESH_TOKEN + userByName.getId(), currentTimeMillis, Integer.parseInt(redisConfig.getRefreshTokenExpireTime()));
            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String token = JwtUtil.sign(String.valueOf(userByName.getId()), currentTimeMillis);
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return new ApsResponse(HttpStatus.OK.value(), "登录成功(Login Success.)", userByName);
        } else {
            throw new ApsUnauthorizedException("帐号或密码错误(Account or Password Error.)");
        }
    }




    @PostMapping("/logout")
    public ApsResponse logout(HttpServletRequest httpServletRequest) {
        long id = userUtil.getUserId();
        UserInfoEntity byId = userInfoService.getById(id);
        if (redisUtil.exists(ApsConstant.Redis.PREFIX_APS_REFRESH_TOKEN + byId.getId())) {
            if (redisUtil.delKey(ApsConstant.Redis.PREFIX_APS_REFRESH_TOKEN + byId.getId())) {
                return new ApsResponse(HttpStatus.OK.value(), "退出成功(Logout Success)", null);
            }
        }
        throw new ApsException("退出失败，Account不存在(Logout Failed. Account does not exist.)");
    }

}
