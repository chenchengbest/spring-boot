package com.ct.common.aop;

import com.ct.common.bean.ApiResult;
import com.ct.common.thread.ExecutorConfig;
import com.ct.common.usrException.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Global exception handler.
 *
 * @author chen.cheng
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * Runtime exception handler result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ApiResult runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(500, ex);
    }

    /**
     * Null pointer exception handler result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public ApiResult nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(508, ex);
    }

    /**
     * Class cast exception handler result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(ClassCastException.class)
    public ApiResult classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(500, ex);
    }

    /**
     * O exception handler result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(IOException.class)
    public ApiResult ioExceptionHandler(IOException ex) {
        return resultFormat(500, ex);
    }

    /**
     * No such method exception handler result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(NoSuchMethodException.class)
    public ApiResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(500, ex);
    }

    /**
     * Index out of bounds exception handler result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ApiResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(500, ex);
    }

    /**
     * Request not readable result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult requestNotReadable(HttpMessageNotReadableException ex) {
        return resultFormat(400, ex);
    }

    /**
     * Request type mismatch result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(TypeMismatchException.class)
    public ApiResult requestTypeMismatch(TypeMismatchException ex) {
        return resultFormat(400, ex);
    }

    /**
     * Request missing servlet request result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return resultFormat(400, ex);
    }

    /**
     * Request 405 string.
     *
     * @param ex the ex
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(405, ex);
    }

    /**
     * Request 406 result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ApiResult request406(HttpMediaTypeNotAcceptableException ex) {
        return resultFormat(406, ex);
    }

    /**
     * Server 500 result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler({
            ConversionNotSupportedException.class, HttpMessageNotWritableException.class
    })
    public ApiResult server500(RuntimeException ex) {
        return resultFormat(500, ex);
    }

    /**
     * Request stack overflow result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler(StackOverflowError.class)
    public ApiResult requestStackOverflow(StackOverflowError ex) {
        return resultFormat(500, ex);
    }

    /**
     * Exception result data bean.
     *
     * @param ex the ex
     * @return the result data bean
     */
    @ResponseBody
    @ExceptionHandler({
            BusinessException.class, Exception.class
    })
    public ApiResult exception(Exception ex) {
        return resultFormat(500, ex);
    }

    /**
     * Result format result data bean.
     *
     * @param <T>  the type parameter
     * @param code the code
     * @param ex   the ex
     * @return the result data bean
     */
    private <T extends Throwable> ApiResult resultFormat(Integer code, T ex) {
        logger.error(">>>>>>>>>>>>>全局异常捕获，堆栈信息：{}<<<<<<<<<<<<<<<<<",ex);
        return ApiResult.fail(code, ex.toString());
    }

    /**
     * Method argument not valid exception handler api result.
     *
     * @param e the e
     * @return the api result
     * @author chen.cheng
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ApiResult methodArgumentNotValidExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errors = new ArrayList<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            // 返回检验信息
            errors.add(error.getDefaultMessage());
        }
        if (!errors.isEmpty()) {
            return ApiResult.fail(errors);
        }
        return ApiResult.fail("服务异常，请稍后重试");
    }
}
