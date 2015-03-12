package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum WorkStatus {
    Active(0), //公开
    Pause(1), //下线
    Delete(2), //删除
    Draft(3); //草稿

    private int code;
    private WorkStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
