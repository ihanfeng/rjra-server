package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IAreaProxy;
import com.hdg.rjra.rdb.proxy.domain.Area;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class AreaProxyImpl extends BaseProxy implements IAreaProxy {

    private static String moduleName = "rdb-area";

    @Override
    public Area findAreaByAreaId(Long areaId) {
        return daoClient.invoke(moduleName, "findAreaByAreaId",
                new Object[]{areaId});
    }

    @Override
    public List<Area> findAreaByCityId(Long cityId) {
        return daoClient.invoke(moduleName, "findAreaByCityId",
                new Object[]{cityId});
    }
}
