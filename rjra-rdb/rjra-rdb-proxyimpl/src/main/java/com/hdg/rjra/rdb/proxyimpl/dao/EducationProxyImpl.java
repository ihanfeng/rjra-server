package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IEducationProxy;
import com.hdg.rjra.rdb.proxy.domain.Education;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class EducationProxyImpl extends BaseProxy implements IEducationProxy {

    private static String moduleName = "rdb-education";

    @Override
    public List<Education> findAllEducation() {
        return daoClient.invoke(moduleName, "findAllEducation",
                new Object[]{});
    }
}
