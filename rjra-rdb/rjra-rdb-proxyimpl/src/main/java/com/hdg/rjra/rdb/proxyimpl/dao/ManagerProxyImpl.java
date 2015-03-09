package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.ICategoryLevel1Proxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IManagerProxy;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;
import com.hdg.rjra.rdb.proxy.domain.Manager;

import java.util.List;

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
}