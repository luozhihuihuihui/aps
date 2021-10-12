package com.hithy.apsmq;


import com.hithy.apsmq.config.MqConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MqConfiguration.class)
public @interface EnableMq {
}
