package com.hdg.rjra.server.model.bo.manager;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class ManagerBo {

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
