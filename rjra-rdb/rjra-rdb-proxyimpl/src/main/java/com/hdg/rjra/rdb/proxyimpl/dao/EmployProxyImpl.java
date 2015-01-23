package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IEmployProxy;
import com.hdg.rjra.rdb.proxy.domain.Employ;
import com.hdg.rjra.rdb.proxy.domain.Pager;

import java.util.Date;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class EmployProxyImpl implements IEmployProxy {

    private static String moduleName = "rdb-employ";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public Employ findEmployByEmployId(Long employId) {
        return daoClient.invoke(moduleName, "findEmployByEmployId",
                new Object[]{employId});
    }

    @Override
    public Pager<Employ> findEmployByUserIdPager(Long userId, Integer employUserStatus, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findEmployByUserIdPager",
                new Object[]{userId, employUserStatus, firstResult, sizeNo});
    }

    @Override
    public Pager<Employ> findEmployByCompanyIdPager(Long companyId, Integer employCompanyStatus, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findEmployByCompanyIdPager",
                new Object[]{companyId, employCompanyStatus, firstResult, sizeNo});
    }

    @Override
    public Long saveEmploy(Employ employ) {
        return daoClient.invoke(moduleName, "saveEmploy",
                new Object[]{employ});
    }

    @Override
    public Integer updateEmployResult(Long employId, Integer employResultStatus, String employResultInfo) {
        return daoClient.invoke(moduleName, "updateEmployResult",
                new Object[]{employId, employResultStatus, employResultInfo});
    }

    @Override
    public Integer updateEmployEntryPlanTime(Long employId, Date planTime) {
        return daoClient.invoke(moduleName, "updateEmployEntryPlanTime",
                new Object[]{employId, planTime});
    }

    @Override
    public Integer updateEmployReport(Long employId, Date reportTime, Integer reportStatus, String reportInfo) {
        return daoClient.invoke(moduleName, "updateEmployReport",
                new Object[]{employId, reportTime, reportStatus, reportInfo});
    }

    @Override
    public Integer updateEmployUserStatus(Long employId, Integer status) {
        return daoClient.invoke(moduleName, "updateEmployUserStatus",
                new Object[]{employId, status});
    }

    @Override
    public Integer updateEmployCompanyStatus(Long employId, Integer status) {
        return daoClient.invoke(moduleName, "updateEmployCompanyStatus",
                new Object[]{employId, status});
    }
}
