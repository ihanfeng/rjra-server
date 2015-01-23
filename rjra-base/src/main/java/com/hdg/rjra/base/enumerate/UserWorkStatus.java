package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum UserWorkStatus {
    Idle(0),
    Working(1);

    private int code;
    private UserWorkStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
