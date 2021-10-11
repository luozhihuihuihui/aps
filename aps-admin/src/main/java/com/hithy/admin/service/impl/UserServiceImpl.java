package com.hithy.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hithy.admin.bo.RegisterBo;
import com.hithy.admin.config.ApsConfig;
import com.hithy.admin.register.Code;
import com.hithy.admin.register.MailBean;
import com.hithy.admin.register.MailUtil;
import com.hithy.admin.service.UserService;
import com.hithy.auth.util.AesCipherUtil;
import com.hithy.auth.util.RedisUtil;
import com.hithy.auth.util.common.StringUtil;
import com.hithy.basedao.dao.UserInfoDao;
import com.hithy.basedao.entity.UserInfoEntity;
import com.hithy.common.ApsResponse;
import com.hithy.common.constant.ApsConstant;
import com.hithy.common.utils.ApsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private Code code;
    @Autowired
    private ApsConfig apsConfig;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ApsResponse register(RegisterBo registerBo) {
        switch (registerBo.getRegisterType()) {
            case ApsConstant.Register.PHONE:
                break;
            case ApsConstant.Register.EMAIL:
                return registerByEmail(registerBo);
            case ApsConstant.Register.QQ:
                break;
            default:
                break;
        }
        return ApsResponse.ok();
    }

    private ApsResponse registerByEmail(RegisterBo registerBo) {
        String email = registerBo.getEmail();
        String code = registerBo.getCode();
        String password = registerBo.getPassword();
        if (StringUtil.isBlank(email) || StringUtil.isBlank(code)) {
            return ApsResponse.error("参数错误");
        }
        if (password.length() > ApsConstant.PASSWORD_MAX_LEN) {
            return ApsResponse.error("密码最多16位");
        }
        String val = (String) Optional.ofNullable(redisUtil.getObject(emailKey(email))).orElse("");
        if (!code.equals(val)) {
            return ApsResponse.error("验证码错误");
        }
        redisUtil.delKey(emailKey(email));
        password = AesCipherUtil.enCrypto(password);
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setPassword(password);
        userInfoEntity.setEmail(email);
        userInfoEntity.setId(ApsUtil.id());
        userInfoDao.insert(userInfoEntity);
        return ApsResponse.ok().data(userInfoEntity);
    }

    @Override
    public ApsResponse sendCode(String email) {
        if (checkEmail(email)) {
            return ApsResponse.error("邮箱已注册");
        }
        String generate = code.generate();
        redisUtil.setObject(emailKey(email), generate, apsConfig.getCodeExpire());
        MailBean mailBean = new MailBean();
        mailBean.setSubject("来自APS的信息");
        mailBean.setText("验证码为:" + generate + "，5分钟内有效");
        mailBean.setTo(email);
        //未收到就重发
        try {
            new Thread(() -> mailUtil.sendMail(mailBean));
        } catch (Exception e) {
            log.info("send email wrong:{}", e.getMessage());
        }
        return ApsResponse.ok("发送成功");
    }

    private boolean checkEmail(String email) {
        int count = userInfoDao.selectCount(new LambdaQueryWrapper<UserInfoEntity>()
                .eq(UserInfoEntity::getEmail, email));
        return count > 0;
    }

    private String emailKey(String email) {
        return ApsConstant.Register.EMAIL + ":" + email;
    }
}
