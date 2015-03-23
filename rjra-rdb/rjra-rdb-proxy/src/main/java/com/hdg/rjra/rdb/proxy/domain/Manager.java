package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/9 0009.
 */
public class Manager implements BaseDomain {
    private Long managerId;
    private String managerName;
    private String managerPwd;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPwd() {
        return managerPwd;
    }

    public void setManagerPwd(String managerPwd) {
        this.managerPwd = managerPwd;
    }
}
