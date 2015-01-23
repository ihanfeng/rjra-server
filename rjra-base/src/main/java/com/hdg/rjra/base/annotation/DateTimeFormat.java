package com.hdg.rjra.base.annotation;

import java.lang.annotation.*;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateTimeFormat {

    String pattern() default "yyyy-MM-dd HH:mm:ss";
}