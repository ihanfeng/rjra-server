package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyScaleProxy;
import com.hdg.rjra.rdb.proxy.domain.CompanyScale;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class CompanyScaleProxyImpl extends BaseProxy implements ICompanyScaleProxy {

    private static String moduleName = "rdb-company-scale";

    @Override
    public List<CompanyScale> findAllCompanyScale() {
        return daoClient.invoke(moduleName, "findAllCompanyScale",
                new Object[]{});
    }
}
