package com.hdg.rjra.base.enumerate;

/**
 * Created by Administrator on 2015/3/26.
 */
public enum  ImportResourceType {
    Company(0),
    Work(1);

    private int code;
    private ImportResourceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
