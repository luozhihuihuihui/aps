package com.hithy.auth.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aps.config")
public class RedisConfig {
    /**
     * AES密码加密私钥(Base64加密)
     */
    private String refreshTokenExpireTime;
    /**
     * JWT认证加密私钥(Base64加密)
     */
    private String encryptAESKey;
    /**
     * AccessToken过期时间-5分钟-5*60(秒为单位)
     */
    private String encryptJWTKey;
    /**
     * RefreshToken过期时间-30分钟-30*60(秒为单位)
     */
    private int accessTokenExpireTime;
    /**
     * Shiro缓存过期时间-5分钟-5*60(秒为单位)(一般设置与AccessToken过期时间一致)
     */
    private int shiroCacheExpireTime;
    /**
     * 是否所有接口都需要登录验证
     */
    private Boolean tokenCheck;

}
