package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.ICityProxy;
import com.hdg.rjra.rdb.proxy.domain.City;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class CityProxyImpl implements ICityProxy {

    private static String moduleName = "rdb-city";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public City findCityByCityId(Long cityId) {
        return daoClient.invoke(moduleName, "findCityByCityId",
                new Object[]{cityId});
    }

    @Override
    public List<City> findCityByProcinceId(Long procinceId) {
        return daoClient.invoke(moduleName, "findCityByProcinceId",
                new Object[]{procinceId});
    }
}
