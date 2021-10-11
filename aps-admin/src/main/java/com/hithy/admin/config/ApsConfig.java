package com.hithy.admin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aps.config")
public class ApsConfig {
    /**
     * 验证码过期时间
     */
    private int codeExpire;
}
