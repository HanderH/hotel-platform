package com.hsj.hotel.common.vo;

import java.io.Serializable;

public class ResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 3593827217136880822L;

    private int code = 200;

    private String message = "成功";

    private T data;
    private String openId;

    public void addError(String message) {
        this.code = 1;
        this.message = message;
    }

    public void addError(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public void addSuccess(T data) {
        this.code = 200;
        this.message = "成功";
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
    public int getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "ResponseBaseBean [code=" + this.code + ", message=" + this.message + ", data=" + this.data + "]";
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
