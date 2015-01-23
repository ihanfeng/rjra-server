package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum FileStatus {
    Display(0),
    Hidden(1);

    private int code;
    private FileStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
