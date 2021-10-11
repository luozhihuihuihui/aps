package com.hithy.auth.realm;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hithy.auth.jwt.JwtToken;
import com.hithy.auth.util.JwtUtil;
import com.hithy.auth.util.RedisUtil;
import com.hithy.auth.util.UserUtil;
import com.hithy.auth.util.common.StringUtil;
import com.hithy.basedao.dao.UserInfoDao;
import com.hithy.basedao.dao.UserPermissionDao;
import com.hithy.basedao.dao.UserRoleBindDao;
import com.hithy.basedao.dao.UserRoleDao;
import com.hithy.basedao.entity.UserInfoEntity;
import com.hithy.basedao.entity.UserPermissionEntity;
import com.hithy.basedao.entity.UserRoleBindEntity;
import com.hithy.basedao.entity.UserRoleEntity;
import com.hithy.common.constant.ApsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ApsShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserRoleBindDao userRoleBindDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserPermissionDao userPermissionDao;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    /**
     * 负责授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String token = principalCollection.getPrimaryPrincipal().toString();
        String userId = JwtUtil.getClaim(token, ApsConstant.JWT.ACCOUNT);
        log.warn("doGetAuthorizationInfo:授权->{}", userId);
        long id = userUtil.getUserId();
        Set<Integer> roleIdS = userRoleBindDao.selectList(new LambdaQueryWrapper<UserRoleBindEntity>()
                .select(UserRoleBindEntity::getRoleId)
                .eq(UserRoleBindEntity::getUserId, id))
                .stream().map(UserRoleBindEntity::getRoleId)
                .collect(Collectors.toSet());
        List<UserRoleEntity> roles = userRoleDao.selectBatchIds(roleIdS);
        Set<Integer> permissionIds = roles.stream().map(UserRoleEntity::getId).collect(Collectors.toSet());
        List<UserPermissionEntity> userPermissionEntities = userPermissionDao.selectBatchIds(permissionIds);
        for (UserRoleEntity role : roles) {
            authorizationInfo.addRole(role.getRoleName());
        }
        for (UserPermissionEntity permission : userPermissionEntities) {
            authorizationInfo.addStringPermission(permission.getPermissionName());
        }
        return authorizationInfo;
    }

    /**
     * 负责身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String account = JwtUtil.getClaim(token, ApsConstant.JWT.ACCOUNT);
        log.warn("doGetAuthorizationInfo:身份认证->{}", token);
        // 帐号为空
        if (StringUtil.isBlank(account)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
        UserInfoEntity user = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfoEntity>()
                .eq(UserInfoEntity::getId, account));
        // 从数据库中查找 user
        if (user == null) {
            return null;
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtil.verify(token) && redisUtil.exists(ApsConstant.Redis.PREFIX_APS_REFRESH_TOKEN + account)) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = redisUtil.getObject(ApsConstant.Redis.PREFIX_APS_REFRESH_TOKEN + account).toString();
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, ApsConstant.JWT.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        }
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }
}