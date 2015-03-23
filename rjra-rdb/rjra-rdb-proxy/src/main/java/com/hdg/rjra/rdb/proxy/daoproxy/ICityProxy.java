package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.City;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICityProxy extends Serializable {

    public City findCityByCityId(Long cityId);

    public List<City> findCityByProvinceId(Long provinceId);
}
