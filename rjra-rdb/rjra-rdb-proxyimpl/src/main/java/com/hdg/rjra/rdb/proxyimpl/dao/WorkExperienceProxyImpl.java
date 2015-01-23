package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IWorkExperienceProxy;
import com.hdg.rjra.rdb.proxy.domain.WorkExperience;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class WorkExperienceProxyImpl implements IWorkExperienceProxy {

    private static String moduleName = "rdb-workexperience";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public List<WorkExperience> findAllWorkExperience() {
        return daoClient.invoke(moduleName, "findAllWorkExperience",
                new Object[]{});
    }
}
