package com.ct.common.bean;

/**
 * The enum Result code.
 *
 * @author chen.cheng
 */
public enum ResultCode {
    /**
     * Success result code.
     *
     * @author chen.cheng
     */
    SUCCESS(200, "成功"),

    /**
     * Fail result code.
     *
     * @author chen.cheng
     */
    FAIL(500, "失败"),
    /**
     * User not login result code.
     *
     * @author chen.cheng
     */
    USER_NOT_LOGIN(2001, "用户未登录");

    /**
     * The Code.
     *
     * @author chen.cheng
     */
    private Integer code;

    /**
     * The Message.
     *
     * @author chen.cheng
     */
    private String message;

    /**
     * Instantiates a new Result code.
     *
     * @param code the code
     * @param message the message
     * @author chen.cheng
     */
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets message.
     *
     * @return the message
     * @author chen.cheng
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets code.
     *
     * @return the code
     * @author chen.cheng
     */
    public Integer getCode() {
        return this.code;
    }
}
