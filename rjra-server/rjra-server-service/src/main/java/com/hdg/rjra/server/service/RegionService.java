package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.City;
import com.hdg.rjra.rdb.proxy.domain.Province;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public interface RegionService {

    public Area findAreaByAreaId(Long areaId);

    public List<Area> findAreaByCityId(Long cityId);

    public City findCityByCityId(Long cityId);

    public List<City> findCityByProvinceId(Long provinceId);

    public Province findProvinceByProvinceId(Long provinceId);

    public List<Province> findAllProvince();
}
