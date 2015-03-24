package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IProvinceProxy;
import com.hdg.rjra.rdb.proxy.domain.Province;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class ProvinceProxyImpl extends BaseProxy implements IProvinceProxy {

    private static String moduleName = "rdb-province";

    @Override
    public Province findProvinceByProvinceId(Long provinceId) {
        return daoClient.invoke(moduleName, "findProvinceByProvinceId",
                new Object[]{provinceId});
    }

    @Override
    public List<Province> findAllProvince() {
        return daoClient.invoke(moduleName, "findAllProvince",
                new Object[]{});
    }
}
