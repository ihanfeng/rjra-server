package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IWorkExperienceProxy;
import com.hdg.rjra.rdb.proxy.domain.WorkExperience;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class WorkExperienceProxyImpl extends BaseProxy implements IWorkExperienceProxy {

    private static String moduleName = "rdb-workexperience";

    @Override
    public List<WorkExperience> findAllWorkExperience() {
        return daoClient.invoke(moduleName, "findAllWorkExperience",
                new Object[]{});
    }
}
