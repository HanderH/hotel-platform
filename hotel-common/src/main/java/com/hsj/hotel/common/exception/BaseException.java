package com.hsj.hotel.common.exception;




import com.hsj.hotel.common.component.SystemConfComponent;

import java.io.Serializable;

public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Integer UNKNOWN_ERROR_CODE=9999;
    /*错误码*/
    private Integer errorCode;

    /* 错误消息*/
    private String message;

    /*错误消息映射，用于映射活动交互语中的占位符*/
    private Object[] args;

    private SystemConfComponent systemConfComponent;
    public BaseException(){
        super();
        this.errorCode = UNKNOWN_ERROR_CODE;
        this.message="系统未知异常！";
    }

    public BaseException(Integer errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message=message;
    }

    public BaseException(Integer errorCode, String message, Object[] args) {
        super();
        this.errorCode = errorCode;
        this.args = args;
        this.message=message;
    }

    public BaseException(Integer errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message=message;
    }

    public BaseException(Integer errorCode, String message, Throwable cause, Object[] args) {
        super(cause);
        this.errorCode = errorCode;
        this.args = args;
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
