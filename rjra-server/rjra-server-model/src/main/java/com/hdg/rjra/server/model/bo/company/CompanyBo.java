package com.hdg.rjra.server.model.bo.company;

import com.hdg.rjra.base.annotation.DateTimeFormat;
import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyBo {
    private Long companyId;
    private String companyName;
    private String companyEmail;
    private String companyMobile;
    private Long companyLogoImageFile;
    private AccountFileBo companyLogoImageFileDetail;
    private Long companyAreaId;
    private Long companyCityId;
    private Long companyProvinceId;
    private String companyAddress;
    private String companyContact;
    private String companyContactMobile;
    private String companyFixedPhone;
    private String companyBizlicenseNumber;
    private Long companyBizlicenseImageFile;
    private AccountFileBo companyBizlicenseImageFileDetail;
    private Integer examineStatus;
    private String examineResultInfo;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String examineTime;
    private Integer companyStatus;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String companyCreateTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String companyUpdateTime;
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

    public String getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
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

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public AccountFileBo getCompanyLogoImageFileDetail() {
        return companyLogoImageFileDetail;
    }

    public void setCompanyLogoImageFileDetail(AccountFileBo companyLogoImageFileDetail) {
        this.companyLogoImageFileDetail = companyLogoImageFileDetail;
    }

    public AccountFileBo getCompanyBizlicenseImageFileDetail() {
        return companyBizlicenseImageFileDetail;
    }

    public void setCompanyBizlicenseImageFileDetail(AccountFileBo companyBizlicenseImageFileDetail) {
        this.companyBizlicenseImageFileDetail = companyBizlicenseImageFileDetail;
    }
}
