package com.ct.common.bean;

import com.ct.common.enums.HttpCodeMessage;
import lombok.Data;

/**
 * The type Result.
 *
 * @param <T> the type parameter
 * @author chen.cheng
 */
@Data
public class Result<T> {
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
     * The Data.
     *
     * @author chen.cheng
     */
    private T data;

    /**
     * Success result.
     *
     * @return the result
     * @author chen.cheng
     */
    public static Result success() {
        Result result = new Result();
        result.code = HttpCodeMessage.SUCCESS.getCode();
        result.message = HttpCodeMessage.SUCCESS.getMessage();
        return result;
    }

    /**
     * Success result.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result
     * @author chen.cheng
     */
    public static <T> Result success(T data) {
        Result result = new Result();
        result.code = HttpCodeMessage.SUCCESS.getCode();
        result.message = HttpCodeMessage.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    /**
     * Failure result.
     *
     * @param httpCodeMessage the http code message
     * @return the result
     * @author chen.cheng
     */
    public static Result failure(HttpCodeMessage httpCodeMessage) {
        Result result = new Result();
        result.code = httpCodeMessage.getCode();
        result.message = httpCodeMessage.getMessage();
        return result;
    }

    /**
     * Failure result.
     *
     * @param <T>             the type parameter
     * @param httpCodeMessage the http code message
     * @param data            the data
     * @return the result
     * @author chen.cheng
     */
    public static <T> Result failure(HttpCodeMessage httpCodeMessage, T data) {
        Result result = new Result();
        result.code = httpCodeMessage.getCode();
        result.message = httpCodeMessage.getMessage();
        result.data = data;
        return result;
    }
}
