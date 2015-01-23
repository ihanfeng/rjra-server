package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IEducationProxy;
import com.hdg.rjra.rdb.proxy.domain.Education;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class EducationProxyImpl implements IEducationProxy {

    private static String moduleName = "rdb-education";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }
    @Override
    public List<Education> findAllEducation() {
        return daoClient.invoke(moduleName, "findAllEducation",
                new Object[]{});
    }
}
