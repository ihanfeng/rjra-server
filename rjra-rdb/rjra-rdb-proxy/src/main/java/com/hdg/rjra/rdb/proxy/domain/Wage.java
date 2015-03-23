package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class Wage implements BaseDomain {

    private Long wageId;

    private String wageInfo;

    public Long getWageId() {
        return wageId;
    }

    public void setWageId(Long wageId) {
        this.wageId = wageId;
    }

    public String getWageInfo() {
        return wageInfo;
    }

    public void setWageInfo(String wageInfo) {
        this.wageInfo = wageInfo;
    }
}
