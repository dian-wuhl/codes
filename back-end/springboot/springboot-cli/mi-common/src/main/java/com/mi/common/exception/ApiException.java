// Copyright 2016-2101 mi.
package com.mi.common.exception;

import com.mi.common.api.IErrorCode;

/**
 * @Description 自定义API异常
 * @Author Dian
 * @Date 2021/8/30 14:22
 * @ModifyDate 2021/8/30 14:22
 * @Version 1.0
 */
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
