package com.hdg.rjra.server.web.controller.param.company;

import com.hdg.rjra.server.web.controller.param.BaseParam;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyParam extends BaseParam {
    private Long companyId;
    private String companyName;
    private String companyEmail;
    private Long companyAreaId;
    private Long companyCityId;
    private Long companyProvinceId;
    private String companyAddress;
    private String companyContact;
    private String companyContactMobile;
    private String companyFixedPhone;
    private String companyBizlicenseNumber;
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

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }
}
