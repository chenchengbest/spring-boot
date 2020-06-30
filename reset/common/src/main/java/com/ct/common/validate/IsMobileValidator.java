package com.ct.common.validate;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Is mobile validator.
 *
 * @author chen.cheng
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    /**
     * The constant mobile_pattern.
     *
     * @author chen.cheng
     */
    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");
    /**
     * The Required.
     *
     * @author chen.cheng
     */
    private boolean required = false;

    /**
     * Is mobile boolean.
     *
     * @param src the src
     * @return the boolean
     * @author chen.cheng
     */
    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(src);
        return m.matches();
    }

    /**
     * Initialize.
     *
     * @param constraintAnnotation the constraint annotation
     * @author chen.cheng
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.isRequired();
    }

    /**
     * Is valid boolean.
     *
     * @param phone the phone
     * @param constraintValidatorContext the constraint validator context
     * @return the boolean
     * @author chen.cheng
     */
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        // 是否为手机号的实现
        if (required) {
            return isMobile(phone);
        }
        else {
            if (StringUtils.isEmpty(phone)) {
                return true;
            }
            else {
                return isMobile(phone);
            }
        }
    }
}
