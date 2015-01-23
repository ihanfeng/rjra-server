package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public enum  GeoStatus {
    Success(0), //成功
    Fail(1) ;//失败

    private int code;
    private GeoStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
