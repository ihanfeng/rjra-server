package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.City;
import com.hdg.rjra.rdb.proxy.domain.Province;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public interface RegionService {

    public AreaBo findAreaByAreaId(Long areaId);

    public List<AreaBo> findAreaByCityId(Long cityId);

    public CityBo findCityByCityId(Long cityId);

    public List<CityBo> findCityByProvinceId(Long provinceId);

    public ProvinceBo findProvinceByProvinceId(Long provinceId);

    public List<ProvinceBo> findAllProvince();
}
