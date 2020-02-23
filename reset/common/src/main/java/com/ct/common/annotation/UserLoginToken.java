package com.ct.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface User login token.
 *
 * @author chen.cheng
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    /**
     * Required boolean.
     *
     * @return the boolean
     * @author chen.cheng
     */
    boolean required() default true;
}