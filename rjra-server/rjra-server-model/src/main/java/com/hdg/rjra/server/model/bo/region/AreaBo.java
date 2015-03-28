package com.hdg.rjra.server.model.bo.region;

/**
 * Created by Administrator on 2015/3/28.
 */
public class AreaBo {
    private Long areaId;
    private String areaName;
    private Long cityId;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
