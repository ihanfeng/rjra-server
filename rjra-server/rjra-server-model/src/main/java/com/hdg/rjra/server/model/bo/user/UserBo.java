package com.hdg.rjra.server.model.bo.user;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;

import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class UserBo {

    private Long userId;
    private String userMobile;
    private String userPwd;
    private Long resumeId;
    private ResumeBo resumeDetail;
    private Long companyId;
    private CompanyBo companyDetail;
    private String userNickName;
    private Long userHeadImageFile;
    private AccountFileBo userHeadImageFileDetail;
    private Integer userGender;
    private Double userLoginLongitude;
    private Double userLoginLatitude;
    private Integer userStatus;
    private Date userCreateTime;
    private Date userUpdateTime;
    private Integer userLoginType;
    private String token;
    private String userDesc;

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

    public ResumeBo getResumeDetail() {
        return resumeDetail;
    }

    public void setResumeDetail(ResumeBo resumeDetail) {
        this.resumeDetail = resumeDetail;
    }

    public CompanyBo getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(CompanyBo companyDetail) {
        this.companyDetail = companyDetail;
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

    public AccountFileBo getUserHeadImageFileDetail() {
        return userHeadImageFileDetail;
    }

    public void setUserHeadImageFileDetail(AccountFileBo userHeadImageFileDetail) {
        this.userHeadImageFileDetail = userHeadImageFileDetail;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
}
