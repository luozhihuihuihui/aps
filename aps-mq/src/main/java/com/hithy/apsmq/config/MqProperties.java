package com.hithy.apsmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rocketmq")
public class MqProperties {
    /**
     * 类似zk
     */
    private String namesrvAddr;
    /**
     * 发送超时时间
     */
    private Integer sendMsgTimeoutMillis;

    /**
     * 失败重试次数
     */
    private Integer reconsumeTimes;
}
