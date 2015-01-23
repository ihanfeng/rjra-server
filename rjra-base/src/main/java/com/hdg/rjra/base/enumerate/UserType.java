package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum UserType {
    Admin(0),
    Company(1),
    User(2);

    private int code;
    private UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
