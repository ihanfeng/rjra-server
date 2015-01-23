package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum ExamineStatus {
    NotPass(0),
    Pending(1),
    Pass(2);

    private int code;
    private ExamineStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
