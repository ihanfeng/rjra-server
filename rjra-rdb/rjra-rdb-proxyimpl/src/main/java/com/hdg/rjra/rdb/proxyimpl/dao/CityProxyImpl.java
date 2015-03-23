package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.ICityProxy;
import com.hdg.rjra.rdb.proxy.domain.City;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class CityProxyImpl extends BaseProxy implements ICityProxy {

    private static String moduleName = "rdb-city";

    @Override
    public City findCityByCityId(Long cityId) {
        return daoClient.invoke(moduleName, "findCityByCityId",
                new Object[]{cityId});
    }

    @Override
    public List<City> findCityByProvinceId(Long provinceId) {
        return daoClient.invoke(moduleName, "findCityByProvinceId",
                new Object[]{provinceId});
    }
}
