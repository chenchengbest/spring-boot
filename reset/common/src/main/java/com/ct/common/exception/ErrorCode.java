package com.ct.common.exception;

/**
 * The enum Error code.
 *
 * @author chen.cheng
 */
public enum ErrorCode {
    /**
     * Success error code.
     *
     * @author chen.cheng
     */
    SUCCESS(0, "成功."),
    /**
     * Unknown error error code.
     *
     * @author chen.cheng
     */
    UNKNOWN_ERROR(-1, "未知错误."),
    /**
     * Param error error code.
     *
     * @author chen.cheng
     */
    PARAM_ERROR(1001, "参数错误."),
    /**
     * Db error error code.
     *
     * @author chen.cheng
     */
    DB_ERROR(3001, "数据库异常."),
    /**
     * Service unavailable error code.
     *
     * @author chen.cheng
     */
    SERVICE_UNAVAILABLE(9001, "服务不可用.");

    /**
     * The Code.
     *
     * @author chen.cheng
     */
    private int code;
    /**
     * The Message.
     *
     * @author chen.cheng
     */
    private String message;

    /**
     * Instantiates a new Error code.
     *
     * @param code    the code
     * @param message the message
     * @author chen.cheng
     */
    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
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
     * Gets message.
     *
     * @return the message
     * @author chen.cheng
     */
    public String getMessage() {
        return this.message;
    }
}
