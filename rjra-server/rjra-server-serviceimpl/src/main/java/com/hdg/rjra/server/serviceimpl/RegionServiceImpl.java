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

    @Override
    public List<AreaBo> findAllArea() {
        List<Area> areaList = areaProxy.findAllArea();
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
        City city = cityProxy.findCityByCityId(cityId);
        CityBo bo = getCityBo(city);
        return bo;
    }

    @Override
    public List<CityBo> findCityByProvinceId(Long provinceId) {
        List<City> list = cityProxy.findCityByProvinceId(provinceId);
        List<CityBo> boList = new ArrayList<CityBo>();
        for (City city : list) {
            CityBo bo = getCityBo(city);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public List<CityBo> findAllCity() {
        List<City> list = cityProxy.findAllCity();
        List<CityBo> boList = new ArrayList<CityBo>();
        for (City city : list) {
            CityBo bo = getCityBo(city);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ProvinceBo findProvinceByProvinceId(Long provinceId) {
        Province province = provinceProxy.findProvinceByProvinceId(provinceId);
        ProvinceBo bo = getProvinceBo(province);
        return bo;
    }

    @Override
    public List<ProvinceBo> findAllProvince() {
        List<Province> list = provinceProxy.findAllProvince();
        List<ProvinceBo> boList = new ArrayList<ProvinceBo>();
        for (Province province : list) {
            ProvinceBo bo = getProvinceBo(province);
            boList.add(bo);
        }
        return boList;
    }
}
