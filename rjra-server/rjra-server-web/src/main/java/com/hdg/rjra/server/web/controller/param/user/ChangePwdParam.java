package com.hdg.rjra.server.web.controller.param.user;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class ChangePwdParam {

    private String mobile;

    private String oldPwd;

    private String newPwd;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
