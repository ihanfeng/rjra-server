package com.hdg.rjra.server.model.param.region;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Administrator on 2015/3/23.
 */
public class RegionParam extends BaseParam {
    private Long areaId;
    private Long cityId;
    private Long provinceId;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}
