package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class Company implements Serializable {

    private Long companyId;
    private String companyName;
    private Long companyType;
    private Long companyScale;
    private Long companyAreaId;
    private Long companyCityId;
    private Long companyProvinceId;
    private String companyAddress;
    private String companyContact;
    private String companyContactMobile;
    private Long companyLogoImageFile;
    private Long companyBizlicenseImageFile;
    private Long companyUserIdCardImageFile;
    private Long companyFacadeImageFile;
    private Integer companyStatus;
    private Date companyCreateTime;
    private Date companyUpdateTime;
    private Date companyChangeTime;
    private String companyDesc;
    private Integer companyExamineStatus;
    private Date companyExamineTime;
    private String companyExamineResult;
    private Long[] companyImages;


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

    public Long getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Long companyType) {
        this.companyType = companyType;
    }

    public Long getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(Long companyScale) {
        this.companyScale = companyScale;
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

    public Long getCompanyLogoImageFile() {
        return companyLogoImageFile;
    }

    public void setCompanyLogoImageFile(Long companyLogoImageFile) {
        this.companyLogoImageFile = companyLogoImageFile;
    }

    public Long getCompanyBizlicenseImageFile() {
        return companyBizlicenseImageFile;
    }

    public void setCompanyBizlicenseImageFile(Long companyBizlicenseImageFile) {
        this.companyBizlicenseImageFile = companyBizlicenseImageFile;
    }

    public Long getCompanyUserIdCardImageFile() {
        return companyUserIdCardImageFile;
    }

    public void setCompanyUserIdCardImageFile(Long companyUserIdCardImageFile) {
        this.companyUserIdCardImageFile = companyUserIdCardImageFile;
    }

    public Long getCompanyFacadeImageFile() {
        return companyFacadeImageFile;
    }

    public void setCompanyFacadeImageFile(Long companyFacadeImageFile) {
        this.companyFacadeImageFile = companyFacadeImageFile;
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

    public Date getCompanyChangeTime() {
        return companyChangeTime;
    }

    public void setCompanyChangeTime(Date companyChangeTime) {
        this.companyChangeTime = companyChangeTime;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public Integer getCompanyExamineStatus() {
        return companyExamineStatus;
    }

    public void setCompanyExamineStatus(Integer companyExamineStatus) {
        this.companyExamineStatus = companyExamineStatus;
    }

    public Date getCompanyExamineTime() {
        return companyExamineTime;
    }

    public void setCompanyExamineTime(Date companyExamineTime) {
        this.companyExamineTime = companyExamineTime;
    }

    public String getCompanyExamineResult() {
        return companyExamineResult;
    }

    public void setCompanyExamineResult(String companyExamineResult) {
        this.companyExamineResult = companyExamineResult;
    }

    public Long[] getCompanyImages() {
        return companyImages;
    }

    public void setCompanyImages(Long[] companyImages) {
        this.companyImages = companyImages;
    }
}
