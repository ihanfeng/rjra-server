package com.hdg.rjra.server.model.param.user;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UserParam extends BaseParam {

    private Long userId;
    private Long resumeId;
    private Long companyId;
    private String userNickName;
    private Integer userGender;
    private Integer userStatus;
    private Integer userLoginType;
    private String userDesc;

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserLoginType() {
        return userLoginType;
    }

    public void setUserLoginType(Integer userLoginType) {
        this.userLoginType = userLoginType;
    }
}
