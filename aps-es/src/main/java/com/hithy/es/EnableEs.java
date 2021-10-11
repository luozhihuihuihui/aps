package com.hithy.es;


import com.hithy.es.config.EsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EsConfiguration.class)
public @interface EnableEs {
}
