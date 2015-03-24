package com.hdg.rjra.rdb.proxy.domain;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class Age implements BaseDomain {

    private Long ageId;

    private String ageInfo;

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public String getAgeInfo() {
        return ageInfo;
    }

    public void setAgeInfo(String ageInfo) {
        this.ageInfo = ageInfo;
    }
}
