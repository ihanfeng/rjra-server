package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class Company implements Serializable {

    private Long companyId;
    private String companyName;
    private String companyEmail;
    private String companyMobile;
    private String companyPwd;
    private Long companyLogoImageFile;
    private Long companyAreaId;
    private Long companyCityId;
    private Long companyProvinceId;
    private String companyAddress;
    private String companyContact;
    private String companyContactMobile;
    private String companyFixedPhone;
    private String companyBizlicenseNumber;
    private Long companyBizlicenseImageFile;
    private Integer examineStatus;
    private String examineResultInfo;
    private Date examineTime;
    private Integer companyStatus;
    private Date companyCreateTime;
    private Date companyUpdateTime;
    private String companyDesc;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getCompanyPwd() {
        return companyPwd;
    }

    public void setCompanyPwd(String companyPwd) {
        this.companyPwd = companyPwd;
    }

    public Long getCompanyLogoImageFile() {
        return companyLogoImageFile;
    }

    public void setCompanyLogoImageFile(Long companyLogoImageFile) {
        this.companyLogoImageFile = companyLogoImageFile;
    }

    public Long getCompanyAreaId() {
        return companyAreaId;
    }

    public void setCompanyAreaId(Long companyAreaId) {
        this.companyAreaId = companyAreaId;
    }

    public Long getCompanyCityId() {
        return companyCityId;
    }

    public void setCompanyCityId(Long companyCityId) {
        this.companyCityId = companyCityId;
    }

    public Long getCompanyProvinceId() {
        return companyProvinceId;
    }

    public void setCompanyProvinceId(Long companyProvinceId) {
        this.companyProvinceId = companyProvinceId;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyContactMobile() {
        return companyContactMobile;
    }

    public void setCompanyContactMobile(String companyContactMobile) {
        this.companyContactMobile = companyContactMobile;
    }

    public String getCompanyFixedPhone() {
        return companyFixedPhone;
    }

    public void setCompanyFixedPhone(String companyFixedPhone) {
        this.companyFixedPhone = companyFixedPhone;
    }

    public String getCompanyBizlicenseNumber() {
        return companyBizlicenseNumber;
    }

    public void setCompanyBizlicenseNumber(String companyBizlicenseNumber) {
        this.companyBizlicenseNumber = companyBizlicenseNumber;
    }

    public Long getCompanyBizlicenseImageFile() {
        return companyBizlicenseImageFile;
    }

    public void setCompanyBizlicenseImageFile(Long companyBizlicenseImageFile) {
        this.companyBizlicenseImageFile = companyBizlicenseImageFile;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineResultInfo() {
        return examineResultInfo;
    }

    public void setExamineResultInfo(String examineResultInfo) {
        this.examineResultInfo = examineResultInfo;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Date getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Date companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public Date getCompanyUpdateTime() {
        return companyUpdateTime;
    }

    public void setCompanyUpdateTime(Date companyUpdateTime) {
        this.companyUpdateTime = companyUpdateTime;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }
}
