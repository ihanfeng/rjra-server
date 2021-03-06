package com.hdg.rjra.server.model.param.company;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyParam extends BaseParam {

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
    private String companyDesc;
    private Integer companyDataType;
    private String companyTag;
    private Integer companyExamineStatus;

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

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
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

    public Integer getCompanyExamineStatus() {
        return companyExamineStatus;
    }

    public void setCompanyExamineStatus(Integer companyExamineStatus) {
        this.companyExamineStatus = companyExamineStatus;
    }
}
