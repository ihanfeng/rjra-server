package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IAreaProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.ICityProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IProvinceProxy;
import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.City;
import com.hdg.rjra.rdb.proxy.domain.Province;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;
import com.hdg.rjra.server.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    IAreaProxy areaProxy;
    @Autowired
    ICityProxy cityProxy;
    @Autowired
    IProvinceProxy provinceProxy;

    @Override
    public AreaBo findAreaByAreaId(Long areaId) {
        Area area = areaProxy.findAreaByAreaId(areaId);
        AreaBo bo = getAreaBo(area);
        return bo;
    }

    @Override
    public List<AreaBo> findAreaByCityId(Long cityId) {
        List<Area> areaList = areaProxy.findAreaByCityId(cityId);
        List<AreaBo> areaBoList = new ArrayList<AreaBo>();
        for (Area area : areaList) {
            AreaBo bo = getAreaBo(area);
            areaBoList.add(bo);
        }
        return areaBoList;
    }

    private AreaBo getAreaBo(Area area) {
        AreaBo bo = new AreaBo();
        ConversionUtils.conversion(area, bo);
        return bo;
    }

    private CityBo getCityBo(City city) {
        CityBo bo = new CityBo();
        ConversionUtils.conversion(city, bo);
        return bo;
    }

    private ProvinceBo getProvinceBo(Province province) {
        ProvinceBo bo = new ProvinceBo();
        ConversionUtils.conversion(province, bo);
        return bo;
    }

    @Override
    public CityBo findCityByCityId(Long cityId) {
//        City city = areaProxy.findCityByCityId(cityId);
//        CityBo bo = getCityBo(city);
//        return bo;
//        return cityProxy.findCityByCityId(cityId);
        return null;
    }

    @Override
    public List<CityBo> findCityByProvinceId(Long provinceId) {
//        return cityProxy.findCityByProvinceId(provinceId);
        return null;
    }

    @Override
    public ProvinceBo findProvinceByProvinceId(Long provinceId) {
//        return provinceProxy.findProvinceByProvinceId(provinceId);
        return null;
    }

    @Override
    public List<ProvinceBo> findAllProvince() {
//        return provinceProxy.findAllProvince();
        return null;
    }
}
