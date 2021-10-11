package com.hithy.auth;

import com.hithy.auth.config.AuthConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AuthConfiguration.class)
public @interface EnableAuth {
}
