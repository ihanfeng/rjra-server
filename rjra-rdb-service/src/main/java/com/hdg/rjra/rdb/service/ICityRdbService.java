package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.City;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICityRdbService extends Serializable {

    public City findCityByCityId(Long cityId);

    public List<City> findCityByProcinceId(Long provinceId);
}
