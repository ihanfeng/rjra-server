package com.hdg.rjra.rdb.proxy.domain;

import com.hdg.rjra.rdb.proxy.utils.DBClass;
import com.hdg.rjra.rdb.proxy.utils.DBField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
@DBClass("account_user")
public class User implements BaseDomain {
    @DBField(value = "user_id", pk = true)
    private Long userId;
    @DBField("user_mobile")
    private String userMobile;
    @DBField("user_pwd")
    private String userPwd;
    private Long resumeId;
    private Long companyId;
    @DBField("user_nickname")
    private String userNickName;
    private Long userHeadImageFile;
    @DBField("user_gender")
    private Integer userGender;
    @DBField("user_login_longitude")
    private Double userLoginLongitude;
    @DBField("user_login_latitude")
    private Double userLoginLatitude;
    @DBField("user_status")
    private Integer userStatus;
    private Date userCreateTime;
    @DBField("user_update_time")
    private Date userUpdateTime;
    private Integer userLoginType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
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

    public Long getUserHeadImageFile() {
        return userHeadImageFile;
    }

    public void setUserHeadImageFile(Long userHeadImageFile) {
        this.userHeadImageFile = userHeadImageFile;
    }

    public Double getUserLoginLongitude() {
        return userLoginLongitude;
    }

    public void setUserLoginLongitude(Double userLoginLongitude) {
        this.userLoginLongitude = userLoginLongitude;
    }

    public Double getUserLoginLatitude() {
        return userLoginLatitude;
    }

    public void setUserLoginLatitude(Double userLoginLatitude) {
        this.userLoginLatitude = userLoginLatitude;
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

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Integer getUserLoginType() {
        return userLoginType;
    }

    public void setUserLoginType(Integer userLoginType) {
        this.userLoginType = userLoginType;
    }
}
