package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum EmployUserStatus {
    Active(0),
    Delete(1);

    private int code;
    private EmployUserStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
