package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum EmployStatus {
    Apply(0), //申请
    Result(1), //有结果
    Entry(2), //计划入职
    Report(3); //报道情况

    private int code;
    private EmployStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
