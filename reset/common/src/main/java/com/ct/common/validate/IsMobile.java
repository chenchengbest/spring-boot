package com.ct.common.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface Is mobile.
 *
 * @author chen.cheng
 */
@Target({
    METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER
})
@Retention(RUNTIME)
@Documented
// 注解的实现类。
@Constraint(validatedBy = {
    IsMobileValidator.class
})
public @interface IsMobile {
    /**
     * Message string.
     *
     * @return the string
     * @author chen.cheng
     */
    String message() default "手机号码格式有问题";

    /**
     * Is required boolean.
     *
     * @return the boolean
     * @author chen.cheng
     */
    boolean isRequired() default false;

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     * @author chen.cheng
     */
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     * @author chen.cheng
     */
    Class<? extends Payload>[] payload() default {};
}
