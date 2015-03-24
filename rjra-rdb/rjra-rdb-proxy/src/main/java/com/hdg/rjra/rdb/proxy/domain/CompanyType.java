package com.hdg.rjra.rdb.proxy.domain;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class CompanyType implements BaseDomain {

    private Long companyTypeId;

    private String companyTypeInfo;

    public Long getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Long companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public String getCompanyTypeInfo() {
        return companyTypeInfo;
    }

    public void setCompanyTypeInfo(String companyTypeInfo) {
        this.companyTypeInfo = companyTypeInfo;
    }
}
