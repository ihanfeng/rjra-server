package com.hdg.rjra.server.model.param.manager;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class ChangePwdParam extends BaseParam {

    private String name;

    private String oldPwd;

    private String newPwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
