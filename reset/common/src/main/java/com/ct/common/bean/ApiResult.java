package com.ct.common.bean;

import java.io.Serializable;

/**
 * The type Api result.
 *
 * @param <T> the type parameter
 * @author chen.cheng
 */
public class ApiResult<T> implements Serializable {
    /**
     * The constant serialVersionUID.
     *
     * @author chen.cheng
     */
    private static final long serialVersionUID = 2538767816320181885L;

    /**
     * The Code.
     *
     * @author chen.cheng
     */
    protected Integer code;

    /**
     * The Is error.
     *
     * @author chen.cheng
     */
    protected boolean isError = false;

    /**
     * The Data.
     *
     * @author chen.cheng
     */
    protected T data;

    /**
     * The Message.
     *
     * @author chen.cheng
     */
    protected String message;

    /**
     * Instantiates a new Api result.
     *
     * @author chen.cheng
     */
    public ApiResult() {
    }

    /**
     * Instantiates a new Api result.
     *
     * @param isError the is error
     * @param code the code
     * @param data the data
     * @param message the message
     * @author chen.cheng
     */
    public ApiResult(boolean isError, Integer code, T data, String message) {
        this.isError = isError;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * Success api result.
     *
     * @return the api result
     * @author chen.cheng
     */
    public static ApiResult success() {
        return success(null);
    }

    /**
     * Success api result.
     *
     * @param <T> the type parameter
     * @param data the data
     * @return the api result
     * @author chen.cheng
     */
    public static <T> ApiResult<T> success(T data) {
        ApiResult apiResult = withCode(false, ResultCode.SUCCESS);
        apiResult.setData(data);
        return apiResult;
    }

    /**
     * Fail api result.
     *
     * @param <T> the type parameter
     * @param data the data
     * @return the api result
     * @author chen.cheng
     */
    public static <T> ApiResult<T> fail(T data) {
        ApiResult apiResult = withCode(true, ResultCode.FAIL);
        apiResult.setData(data);
        return apiResult;
    }

    /**
     * Fail api result.
     *
     * @param code    the code
     * @param message the message
     * @return the api result
     * @author chen.cheng
     */
    public static  ApiResult fail(Integer code , String message) {
     return new ApiResult(true, code, "", message);
    }

    /**
     * Fail api result.
     *
     * @param message the message
     * @return the api result
     * @author chen.cheng
     */
    public static ApiResult fail(String message) {
        ApiResult apiResult = withCode(true, ResultCode.FAIL);
        apiResult.setMessage(message);
        return apiResult;
    }

    /**
     * With code api result.
     *
     * @param isError the is error
     * @param resultCode the result code
     * @return the api result
     * @author chen.cheng
     */
    public static ApiResult withCode(boolean isError, ResultCode resultCode) {
        return withCode(isError, resultCode, null);
    }

    /**
     * With code api result.
     *
     * @param <T> the type parameter
     * @param isError the is error
     * @param resultCode the result code
     * @param data the data
     * @return the api result
     * @author chen.cheng
     */
    public static <T> ApiResult<T> withCode(boolean isError, ResultCode resultCode, T data) {
        return new ApiResult(isError, resultCode.getCode(), data, resultCode.getMessage());
    }

    /**
     * Gets code.
     *
     * @return the code
     * @author chen.cheng
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     * @author chen.cheng
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Is error boolean.
     *
     * @return the boolean
     * @author chen.cheng
     */
    public boolean getIsError() {
        return isError;
    }

    /**
     * Sets error.
     *
     * @param error the error
     * @author chen.cheng
     */
    public void setIsError(boolean error) {
        isError = error;
    }

    /**
     * Gets data.
     *
     * @return the data
     * @author chen.cheng
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     * @author chen.cheng
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets message.
     *
     * @return the message
     * @author chen.cheng
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     * @author chen.cheng
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
