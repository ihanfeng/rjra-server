package com.hdg.rjra.server.model.bo.company;

import com.hdg.rjra.server.model.bo.file.AccountFileBo;

import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyBo {

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
    private String companyEmail;
    private Long companyLogoImageFile;
    private AccountFileBo companyLogoImageFileDetail;
    private Long companyBizlicenseImageFile;
    private AccountFileBo companyBizlicenseImageFileDetail;
    private Long companyUserIdCardImageFile;
    private AccountFileBo companyUserIdCardImageFileDetail;
    private Long companyFacadeImageFile;
    private Long[] companyImages;
    private List<AccountFileBo> companyImagesDetail;
    private AccountFileBo companyFacadeImageFileDetail;
    private Integer companyStatus;
    private Date companyCreateTime;
    private Date companyUpdateTime;
    private Date companyChangeTime;
    private String companyDesc;
    private Integer companyExamineStatus;
    private Date companyExamineTime;
    private Date companyExamineSubmitTime;
    private String companyExamineResult;
    private Integer companyDataType;
    private String companyTag;
    private Long companyExamineReviewerId;
    private String companyExamineReviewerName;
    private Date companyImportTime;
    private Long companyImportOperatorId;
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

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Long[] getCompanyImages() {
        return companyImages;
    }

    public void setCompanyImages(Long[] companyImages) {
        this.companyImages = companyImages;
    }

    public List<AccountFileBo> getCompanyImagesDetail() {
        return companyImagesDetail;
    }

    public void setCompanyImagesDetail(List<AccountFileBo> companyImagesDetail) {
        this.companyImagesDetail = companyImagesDetail;
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

    public Date getCompanyExamineSubmitTime() {
        return companyExamineSubmitTime;
    }

    public void setCompanyExamineSubmitTime(Date companyExamineSubmitTime) {
        this.companyExamineSubmitTime = companyExamineSubmitTime;
    }

    public String getCompanyExamineResult() {
        return companyExamineResult;
    }

    public void setCompanyExamineResult(String companyExamineResult) {
        this.companyExamineResult = companyExamineResult;
    }

    public Integer getCompanyDataType() {
        return companyDataType;
    }

    public void setCompanyDataType(Integer companyDataType) {
        this.companyDataType = companyDataType;
    }

    public String getCompanyTag() {
        return companyTag;
    }

    public void setCompanyTag(String companyTag) {
        this.companyTag = companyTag;
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
