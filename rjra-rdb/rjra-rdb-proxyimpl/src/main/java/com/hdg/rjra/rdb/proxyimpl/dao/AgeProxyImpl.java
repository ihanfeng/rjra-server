package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IAgeProxy;
import com.hdg.rjra.rdb.proxy.domain.Age;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class AgeProxyImpl extends BaseProxy implements IAgeProxy {

    private static String moduleName = "rdb-age";

    @Override
    public List<Age> findAllAge() {
        return daoClient.invoke(moduleName, "findAllAge",
                new Object[]{});
    }
}
