package com.hdg.rjra.server.model.param;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class LoginParam {

    private String mobile;

    private String code;

    private String pwd;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
