package com.johnny.exception;

/**
 * This Exception is self-defined common exception for Spring-common
 * <p>
 * <p>
 * Created by johnny01.yang on 2016/7/12.
 */
public class SpringCommonException extends RuntimeException {

    public SpringCommonException() {
        super();
    }

    public SpringCommonException(String message) {
        super(message);
    }

    public SpringCommonException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SpringCommonException(Throwable throwable) {
        super(throwable);
    }

    public SpringCommonException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }


}
