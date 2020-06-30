package com.ct.common.usrException;

/**
 * The type Cust exception.
 */
public class CustException extends Exception {
    /**
     * Instantiates a new Cust exception.
     */
    public CustException() {
        super();
    }

    /**
     * Instantiates a new Cust exception.
     *
     * @param message the message
     */
    public CustException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Cust exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public CustException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Cust exception.
     *
     * @param cause the cause
     */
    public CustException(Throwable cause) {
        super(cause);
    }
}
