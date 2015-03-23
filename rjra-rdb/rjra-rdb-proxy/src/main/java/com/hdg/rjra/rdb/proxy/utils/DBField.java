package com.hdg.rjra.rdb.proxy.utils;

import java.lang.annotation.*;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DBField {
        String value();
        boolean pk() default false;
}
