package com.ct.common.exception;

import java.util.Collection;
import java.util.Map;

/**
 * The type Assert.
 *
 * @author chen.cheng
 */
public class Assert {
    /**
     * Instantiates a new Assert.
     *
     * @author chen.cheng
     */
    public Assert() {
    }

    /**
     * Is true.
     *
     * @param expression the expression
     * @param errorCode  the error code
     * @param message    the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void isTrue(boolean expression, int errorCode, String message) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errorCode, message);
        }
    }

    /**
     * Is true.
     *
     * @param expression the expression
     * @param errorCode  the error code
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void isTrue(boolean expression, ErrorCode errorCode) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errorCode.getCode(), errorCode.getMessage());
        }
    }

    /**
     * Is true.
     *
     * @param expression the expression
     * @param errorCode  the error code
     * @param message    the message
     * @param ext        the ext
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void isTrue(boolean expression, int errorCode, String message, Object ext) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errorCode, message, ext);
        }
    }

    /**
     * Is null.
     *
     * @param object    the object
     * @param errorCode the error code
     * @param message   the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void isNull(Object object, int errorCode, String message) throws BusinessException {
        if (object != null) {
            throw new BusinessException(errorCode, message);
        }
    }

    /**
     * Is null.
     *
     * @param object    the object
     * @param errorCode the error code
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void isNull(Object object, ErrorCode errorCode) throws BusinessException {
        if (object != null) {
            throw new BusinessException(errorCode.getCode(), errorCode.getMessage());
        }
    }

    /**
     * Not null.
     *
     * @param object    the object
     * @param errorCode the error code
     * @param message   the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void notNull(Object object, int errorCode, String message) throws BusinessException {
        if (object == null) {
            throw new BusinessException(errorCode, message);
        }
    }

    /**
     * Not null.
     *
     * @param object    the object
     * @param errorCode the error code
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void notNull(Object object, ErrorCode errorCode) throws BusinessException {
        if (object == null) {
            throw new BusinessException(errorCode.getCode(), errorCode.getMessage());
        }
    }

    /**
     * Not empty.
     *
     * @param array     the array
     * @param errorCode the error code
     * @param message   the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void notEmpty(Object[] array, int errorCode, String message) throws BusinessException {
        if (array == null || array.length == 0) {
            throw new BusinessException(errorCode, message);
        }
    }

    /**
     * Not empty.
     *
     * @param <T>        the type parameter
     * @param collection the collection
     * @param errorCode  the error code
     * @param message    the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static <T> void notEmpty(Collection<T> collection, int errorCode, String message) throws BusinessException {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(errorCode, message);
        }
    }

    /**
     * Not empty.
     *
     * @param <K>       the type parameter
     * @param <V>       the type parameter
     * @param map       the map
     * @param errorCode the error code
     * @param message   the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static <K, V> void notEmpty(Map<K, V> map, int errorCode, String message) throws BusinessException {
        if (map == null || map.isEmpty()) {
            throw new BusinessException(errorCode, message);
        }
    }

    /**
     * Not empty.
     *
     * @param str       the str
     * @param errorCode the error code
     * @param message   the message
     * @throws BusinessException the business exception
     * @author chen.cheng
     */
    public static void notEmpty(String str, int errorCode, String message) throws BusinessException {
        if (str == null || str.length() == 0) {
            throw new BusinessException(errorCode, message);
        }
    }
}
