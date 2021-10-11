package com.hithy.auth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @Author YiJia
 * @Description 从应用上下文中获取注册的服务
 * @Date 15:14 2019/3/25
 * @Param null
 * @Return
 **/
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger("SpringBeanUtil");

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    /**
     * 获取Ioc上下文对象
     */
    public static ApplicationContext getApplicationContext() throws BeansException {
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        return applicationContext != null ? (T) applicationContext.getBean(name) : null;
    }

    public static <T> T getBean(String name, Class<T> clz) {
        return applicationContext != null ? applicationContext.getBean(name, clz) : null;
    }

    public static <T> T getBean(Class<T> clz) {
        return applicationContext != null ? applicationContext.getBean(clz) : null;
    }

}
