package com.hdg.rjra.rdb.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class Welfare implements Serializable {

    private Long welfareId;

    private String welfareInfo;

    public Long getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(Long welfareId) {
        this.welfareId = welfareId;
    }

    public String getWelfareInfo() {
        return welfareInfo;
    }

    public void setWelfareInfo(String welfareInfo) {
        this.welfareInfo = welfareInfo;
    }
}
