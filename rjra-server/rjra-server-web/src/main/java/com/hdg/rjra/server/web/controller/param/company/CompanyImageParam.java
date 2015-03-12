package com.hdg.rjra.server.web.controller.param.company;

import com.hdg.rjra.server.web.controller.param.BaseParam;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CompanyImageParam extends BaseParam {

    private Long companyId;
    private Long companyDeleteImageId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyDeleteImageId() {
        return companyDeleteImageId;
    }

    public void setCompanyDeleteImageId(Long companyDeleteImageId) {
        this.companyDeleteImageId = companyDeleteImageId;
    }
}
