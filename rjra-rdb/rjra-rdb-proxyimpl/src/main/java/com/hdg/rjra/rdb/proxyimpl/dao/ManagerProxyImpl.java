package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IManagerProxy;
import com.hdg.rjra.rdb.proxy.domain.Manager;

/**
 * Created by Rock on 2015/3/9 0009.
 */
public class ManagerProxyImpl extends BaseProxy implements IManagerProxy {

    private static String moduleName = "rdb-manager";

    @Override
    public Manager findManagerByNameAndPwd(String name, String pwd) {

        return daoClient.invoke(moduleName, "findManagerByNameAndPwd",
                new Object[]{name, pwd});
    }

    @Override
    public Integer updateManagerPwd(Long managerId, String pwd) {

        return daoClient.invoke(moduleName, "updateManagerPwd",
                new Object[]{managerId, pwd});
    }
}