package com.hdg.rjra.rdb.serviceimpl;

/**
 * Created by Rock on 2015/2/16 0016.
 */
public enum ResultType {
    SUCCESS("0"), BUSINESS_ERROR("-1"), PARAM_ERROR("-2");
    private String code;

    private ResultType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
