package com.hdg.rjra.rdb.proxy.domain;

import com.hdg.rjra.rdb.proxy.utils.DBClass;
import com.hdg.rjra.rdb.proxy.utils.DBField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
@DBClass("user_company")
public class Company implements BaseDomain {

    @DBField(value = "company_Id", pk = true)
    private Long companyId;
    @DBField("company_name")
    private String companyName;
    @DBField("company_type")
    private Long companyType;
    @DBField("company_scale")
    private Long companyScale;
    @DBField("company_area_id")
    private Long companyAreaId;
    @DBField("company_city_id")
    private Long companyCityId;
    @DBField("company_province_id")
    private Long companyProvinceId;
    @DBField("company_address")
    private String companyAddress;
    @DBField("company_contact")
    private String companyContact;
    @DBField("company_contact_mobile")
    private String companyContactMobile;
    @DBField("company_email")
    private String companyEmail;
    private Long companyLogoImageFile;
    private Long companyBizlicenseImageFile;
    private Long companyUserIdCardImageFile;
    private Long companyFacadeImageFile;
    @DBField("company_status")
    private Integer companyStatus;
    private Date companyCreateTime;
    @DBField("company_update_time")
    private Date companyUpdateTime;
    @DBField("company_change_time")
    private Date companyChangeTime;
    @DBField("company_desc")
    private String companyDesc;
    @DBField("company_examine_status")
    private Integer companyExamineStatus;
    @DBField("company_examine_time")
    private Date companyExamineTime;
    @DBField("company_examine_result")
    private String companyExamineResult;
    @DBField("company_examine_submit_time")
    private Date companyExamineSubmitTime;
    @DBField("company_examine_reviewer_id")
    private Long companyExamineReviewerId;
    @DBField("company_examine_reviewer_name")
    private String companyExamineReviewerName;
    private Long[] companyImages;
    @DBField("company_tag")
    private String companyTag;
    @DBField("company_data_type")
    private Integer companyDataType;
    @DBField("company_import_time")
    private Date companyImportTime;
    @DBField("company_import_operator_id")
    private Long companyImportOperatorId;
    @DBField("company_import_operator_name")
    private String companyImportOperatorName;

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

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Date getCompanyExamineSubmitTime() {
        return companyExamineSubmitTime;
    }

    public void setCompanyExamineSubmitTime(Date companyExamineSubmitTime) {
        this.companyExamineSubmitTime = companyExamineSubmitTime;
    }

    public Long getCompanyExamineReviewerId() {
        return companyExamineReviewerId;
    }

    public void setCompanyExamineReviewerId(Long companyExamineReviewerId) {
        this.companyExamineReviewerId = companyExamineReviewerId;
    }

    public String getCompanyExamineReviewerName() {
        return companyExamineReviewerName;
    }

    public void setCompanyExamineReviewerName(String companyExamineReviewerName) {
        this.companyExamineReviewerName = companyExamineReviewerName;
    }

    public String getCompanyTag() {
        return companyTag;
    }

    public void setCompanyTag(String companyTag) {
        this.companyTag = companyTag;
    }

    public Integer getCompanyDataType() {
        return companyDataType;
    }

    public void setCompanyDataType(Integer companyDataType) {
        this.companyDataType = companyDataType;
    }

    public Date getCompanyImportTime() {
        return companyImportTime;
    }

    public void setCompanyImportTime(Date companyImportTime) {
        this.companyImportTime = companyImportTime;
    }

    public Long getCompanyImportOperatorId() {
        return companyImportOperatorId;
    }

    public void setCompanyImportOperatorId(Long companyImportOperatorId) {
        this.companyImportOperatorId = companyImportOperatorId;
    }

    public String getCompanyImportOperatorName() {
        return companyImportOperatorName;
    }

    public void setCompanyImportOperatorName(String companyImportOperatorName) {
        this.companyImportOperatorName = companyImportOperatorName;
    }
}
