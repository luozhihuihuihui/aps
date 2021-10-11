package com.hithy.basedao;

import com.hithy.basedao.config.DaoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DaoConfiguration.class)
public @interface EnableDao {
}
