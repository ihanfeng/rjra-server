package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum CompanyExamineStatus {
    Pending(0),
    Pass(1),
    NotPass(2);

    private int code;
    private CompanyExamineStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
