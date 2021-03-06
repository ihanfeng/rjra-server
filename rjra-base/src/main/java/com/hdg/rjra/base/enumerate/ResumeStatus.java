package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum ResumeStatus {
    Active(0),
    Pause(1),
    Delete(2);

    private int code;
    private ResumeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
