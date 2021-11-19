// Copyright 2016-2101 mi.
package com.mi.common.exception;

import com.mi.common.api.IErrorCode;

/**
 * @Description 断言处理类，用于抛出各种API异常
 * @Author Dian
 * @Date 2021/8/30 14:22
 * @ModifyDate 2021/8/30 14:22
 * @Version 1.0
 */
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

}

