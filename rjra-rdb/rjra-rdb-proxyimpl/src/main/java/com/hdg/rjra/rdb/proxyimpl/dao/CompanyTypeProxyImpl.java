package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyTypeProxy;
import com.hdg.rjra.rdb.proxy.domain.CompanyType;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class CompanyTypeProxyImpl extends BaseProxy implements ICompanyTypeProxy {

    private static String moduleName = "rdb-company-type";

    @Override
    public List<CompanyType> findAllCompanyType() {
        return daoClient.invoke(moduleName, "findAllCompanyType",
                new Object[]{});
    }
}
