package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IWageProxy;
import com.hdg.rjra.rdb.proxy.domain.Wage;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class WageProxyImpl extends BaseProxy implements IWageProxy {

    private static String moduleName = "rdb-wage";

    @Override
    public List<Wage> findAllWage() {
        return daoClient.invoke(moduleName, "findAllWage",
                new Object[]{});
    }
}
