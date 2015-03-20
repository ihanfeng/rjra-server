package com.hdg.rjra.base.enumerate;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public enum UserInviteUserStatus {
    Active(0),
    Delete(1);

    private int code;
    private UserInviteUserStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
