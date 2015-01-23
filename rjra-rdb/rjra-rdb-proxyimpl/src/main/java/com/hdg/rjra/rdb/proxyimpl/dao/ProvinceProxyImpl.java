package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IProvinceProxy;
import com.hdg.rjra.rdb.proxy.domain.Province;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class ProvinceProxyImpl implements IProvinceProxy {

    private static String moduleName = "rdb-province";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }
    @Override
    public Province findProvinceByProvinceId(Long provinceId) {
        return daoClient.invoke(moduleName, "findProvinceByProvinceId",
                new Object[]{provinceId});
    }

    @Override
    public Province findAllProvince() {
        return daoClient.invoke(moduleName, "findAllProvince",
                new Object[]{});
    }
}
