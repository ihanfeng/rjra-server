package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum ExamineResourceType {
    Company(0),
    Work(1);

    private int code;
    private ExamineResourceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
