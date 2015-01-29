package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/29 0029.
 */
public enum ResumeWorkStatus {
    Idle(0),
    Working(1),
    WorkingWantChange(2);

    private int code;
    private ResumeWorkStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
