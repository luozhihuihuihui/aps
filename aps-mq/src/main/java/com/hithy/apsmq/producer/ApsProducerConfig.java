package com.hithy.apsmq.producer;

import com.hithy.apsmq.config.MqProperties;
import com.hithy.apsmq.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApsProducerConfig {

    @Autowired
    private MqProperties mqProperties;

    /**
     * 初始化生产者
     *
     * @return
     */
    @Bean
    public DefaultMQProducer defaultProducer() throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer(MqConstant.ConsumeGroup.APS_PROJECT);
        // 设置NameServer的地址
        producer.setNamesrvAddr(mqProperties.getNamesrvAddr());
        // 设置发送消息超时时间
        producer.setSendMsgTimeout(mqProperties.getSendMsgTimeoutMillis());
        // 设置重试次数
        producer.setRetryTimesWhenSendFailed(mqProperties.getReconsumeTimes());
        // 启动Producer实例
        producer.start();

        return producer;
    }

}
