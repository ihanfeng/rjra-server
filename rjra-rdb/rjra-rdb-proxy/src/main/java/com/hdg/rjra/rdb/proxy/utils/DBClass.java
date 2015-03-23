package com.hdg.rjra.rdb.proxy.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2015/3/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.TYPE })
public @interface DBClass {
    String value();
}
