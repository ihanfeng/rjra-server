package com.hdg.rjra.rdb.proxy.domain;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CompanyScale implements BaseDomain {

    private Long companyScaleId;

    private String companyScaleInfo;

    public Long getCompanyScaleId() {
        return companyScaleId;
    }

    public void setCompanyScaleId(Long companyScaleId) {
        this.companyScaleId = companyScaleId;
    }

    public String getCompanyScaleInfo() {
        return companyScaleInfo;
    }

    public void setCompanyScaleInfo(String companyScaleInfo) {
        this.companyScaleInfo = companyScaleInfo;
    }
}
