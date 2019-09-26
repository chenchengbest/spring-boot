package com.ct.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Log.
 *
 * @author chen.cheng
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * Title string.
     *
     * @return the string
     * @author chen.cheng
     */
    String title() default "";

    /**
     * Action string.
     *
     * @return the string
     * @author chen.cheng
     */
    String action() default "";
}
