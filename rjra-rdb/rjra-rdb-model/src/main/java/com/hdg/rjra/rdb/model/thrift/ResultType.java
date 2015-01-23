package com.hdg.rjra.rdb.model.thrift;

/**
 * Created by Rock on 2014/9/20.
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
