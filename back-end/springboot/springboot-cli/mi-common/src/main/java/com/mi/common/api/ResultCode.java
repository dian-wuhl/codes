// Copyright 2016-2101 mi.
package com.mi.common.api;

/**
 * @Description 常用API操作码
 * @Author Dian
 * @Date 2021/8/30 11:55
 * @ModifyDate 2021/8/30 11:55
 * @Version 1.0
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(503, "参数检验失败"),
    UNAUTHORIZED(401, "登录已过时"),
    FORBIDDEN(403, "没有相关权限"),
    UNKNOWN(99999, "未知异常，请稍后再试！");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
