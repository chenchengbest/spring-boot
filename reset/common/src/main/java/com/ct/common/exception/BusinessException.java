package com.ct.common.exception;

/**
 * The type Business exception.
 *
 * @author chen.cheng
 */
public class BusinessException extends RuntimeException {
    /**
     * The Code.
     *
     * @author chen.cheng
     */
    protected int code;
    /**
     * The Message.
     *
     * @author chen.cheng
     */
    protected final String message;
    /**
     * The Ext.
     *
     * @author chen.cheng
     */
    private Object ext;

    /**
     * Instantiates a new Business exception.
     *
     * @param message the message
     * @author chen.cheng
     */
    public BusinessException(String message) {
        this.message = message;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param code    the code
     * @param message the message
     * @author chen.cheng
     */
    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param message the message
     * @param e       the e
     * @author chen.cheng
     */
    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param code    the code
     * @param message the message
     * @param e       the e
     * @author chen.cheng
     */
    public BusinessException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param code    the code
     * @param message the message
     * @param ext     the ext
     * @author chen.cheng
     */
    public BusinessException(int code, String message, Object ext) {
        this.code = code;
        this.message = message;
        this.ext = ext;
    }

    /**
     * Gets message.
     *
     * @return the message
     * @author chen.cheng
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets code.
     *
     * @return the code
     * @author chen.cheng
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Gets ext.
     *
     * @return the ext
     * @author chen.cheng
     */
    public Object getExt() {
        return this.ext;
    }
}
