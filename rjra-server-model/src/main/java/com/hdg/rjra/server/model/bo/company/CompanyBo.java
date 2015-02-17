package com.hdg.rjra.server.model.bo.company;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyBo implements Serializable {

    private Long companyId;
    private String companyName;
    private Long companyAreaId;
    private Long companyCityId;
    private Long companyProvinceId;
    private String companyAddress;
    private String companyContact;
    private String companyContactMobile;
    private Long companyLogoImageFile;
    private AccountFileBo companyLogoImageFileDetail;
    private Long companyBizlicenseImageFile;
    private AccountFileBo companyBizlicenseImageFileDetail;
    private Long companyUserIdCardImageFile;
    private AccountFileBo companyUserIdCardImageFileDetail;
    private Long companyFacadeImageFile;
    private AccountFileBo companyFacadeImageFileDetail;
    private Integer companyStatus;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String companyCreateTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String companyUpdateTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String companyChangeTime;
    private String companyDesc;
    private Integer companyExamineStatus;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String companyExamineTime;
    private String companyExamineResult;

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

    public AccountFileBo getCompanyLogoImageFileDetail() {
        return companyLogoImageFileDetail;
    }

    public void setCompanyLogoImageFileDetail(AccountFileBo companyLogoImageFileDetail) {
        this.companyLogoImageFileDetail = companyLogoImageFileDetail;
    }

    public Long getCompanyBizlicenseImageFile() {
        return companyBizlicenseImageFile;
    }

    public void setCompanyBizlicenseImageFile(Long companyBizlicenseImageFile) {
        this.companyBizlicenseImageFile = companyBizlicenseImageFile;
    }

    public AccountFileBo getCompanyBizlicenseImageFileDetail() {
        return companyBizlicenseImageFileDetail;
    }

    public void setCompanyBizlicenseImageFileDetail(AccountFileBo companyBizlicenseImageFileDetail) {
        this.companyBizlicenseImageFileDetail = companyBizlicenseImageFileDetail;
    }

    public Long getCompanyUserIdCardImageFile() {
        return companyUserIdCardImageFile;
    }

    public void setCompanyUserIdCardImageFile(Long companyUserIdCardImageFile) {
        this.companyUserIdCardImageFile = companyUserIdCardImageFile;
    }

    public AccountFileBo getCompanyUserIdCardImageFileDetail() {
        return companyUserIdCardImageFileDetail;
    }

    public void setCompanyUserIdCardImageFileDetail(AccountFileBo companyUserIdCardImageFileDetail) {
        this.companyUserIdCardImageFileDetail = companyUserIdCardImageFileDetail;
    }

    public Long getCompanyFacadeImageFile() {
        return companyFacadeImageFile;
    }

    public void setCompanyFacadeImageFile(Long companyFacadeImageFile) {
        this.companyFacadeImageFile = companyFacadeImageFile;
    }

    public AccountFileBo getCompanyFacadeImageFileDetail() {
        return companyFacadeImageFileDetail;
    }

    public void setCompanyFacadeImageFileDetail(AccountFileBo companyFacadeImageFileDetail) {
        this.companyFacadeImageFileDetail = companyFacadeImageFileDetail;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(String companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getCompanyUpdateTime() {
        return companyUpdateTime;
    }

    public void setCompanyUpdateTime(String companyUpdateTime) {
        this.companyUpdateTime = companyUpdateTime;
    }

    public String getCompanyChangeTime() {
        return companyChangeTime;
    }

    public void setCompanyChangeTime(String companyChangeTime) {
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

    public String getCompanyExamineTime() {
        return companyExamineTime;
    }

    public void setCompanyExamineTime(String companyExamineTime) {
        this.companyExamineTime = companyExamineTime;
    }

    public String getCompanyExamineResult() {
        return companyExamineResult;
    }

    public void setCompanyExamineResult(String companyExamineResult) {
        this.companyExamineResult = companyExamineResult;
    }
}
