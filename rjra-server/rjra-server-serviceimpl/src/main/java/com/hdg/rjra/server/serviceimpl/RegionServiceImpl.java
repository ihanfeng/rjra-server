package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.IAreaProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.ICityProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IProvinceProxy;
import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.City;
import com.hdg.rjra.rdb.proxy.domain.Province;
import com.hdg.rjra.server.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
public class RegionServiceImpl implements RegionService {

    @Autowired
    IAreaProxy areaProxy;
    @Autowired
    ICityProxy cityProxy;
    @Autowired
    IProvinceProxy provinceProxy;

    @Override
    public Area findAreaByAreaId(Long areaId) {
        return areaProxy.findAreaByAreaId(areaId);
    }

    @Override
    public List<Area> findAreaByCityId(Long cityId) {
        return areaProxy.findAreaByCityId(cityId);
    }

    @Override
    public City findCityByCityId(Long cityId) {
        return cityProxy.findCityByCityId(cityId);
    }

    @Override
    public List<City> findCityByProvinceId(Long provinceId) {
        return cityProxy.findCityByProvinceId(provinceId);
    }

    @Override
    public Province findProvinceByProvinceId(Long provinceId) {
        return provinceProxy.findProvinceByProvinceId(provinceId);
    }

    @Override
    public List<Province> findAllProvince() {
        return provinceProxy.findAllProvince();
    }
}
