package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum FileType {
    Image(0),
    Sound(1);

    private int code;
    private FileType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
