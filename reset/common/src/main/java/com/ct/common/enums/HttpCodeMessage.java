package com.ct.common.enums;

/**
 * The enum Http code message.
 *
 * @author chen.cheng
 */
public enum HttpCodeMessage {
    /**
     * Instantiates a new Success.
     *
     * @author chen.cheng
     */
    SUCCESS(1, "成功！"),

    /**
     * Instantiates a new Param is invalid.
     *
     * @author chen.cheng
     */
    PARAM_IS_INVALID(1001, "参数无效！"),

    /**
     * Instantiates a new Param is blank.
     *
     * @author chen.cheng
     */
    PARAM_IS_BLANK(1002, "参数为空！"),

    /**
     * Instantiates a new Param type bind error.
     *
     * @author chen.cheng
     */
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误！"),

    /**
     * Instantiates a new Param not complete.
     *
     * @author chen.cheng
     */
    PARAM_NOT_COMPLETE(1004, "参数缺失！");

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
     * Instantiates a new Http code message.
     *
     * @param code    the code
     * @param message the message
     * @author chen.cheng
     */
    HttpCodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public Integer getCode(){
        return this.code;
    }
}
