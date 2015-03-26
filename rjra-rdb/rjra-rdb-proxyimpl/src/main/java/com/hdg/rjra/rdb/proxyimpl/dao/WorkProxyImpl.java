package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Work;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class WorkProxyImpl extends BaseProxy implements IWorkProxy {

    private static String moduleName = "rdb-work";

    @Override
    public Work findWorkByWorkId(Long workId) {
        return daoClient.invoke(moduleName, "findWorkByWorkId",
                new Object[]{workId});
    }

    @Override
    public Pager<Work> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllWorkByParamPager",
                new Object[]{param, status, firstResult, sizeNo});
    }

    @Override
    public Pager<Work> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findNearWorkByParamPager",
                new Object[]{param, lng, lat, raidus, status, firstResult, sizeNo});
    }

    @Override
    public Integer updateWork(Work work) {
        return daoClient.invoke("rdb-common", "updateByPk",
                new Object[]{work});
    }

    @Override
    public Integer updateWorkStatus(Long workId, Integer status) {
        return daoClient.invoke(moduleName, "updateWorkStatus",
                new Object[]{workId, status});
    }

    @Override
    public Long saveWork(Work work) {
        return daoClient.invoke(moduleName, "saveWork",
                new Object[]{work});
    }

    @Override
    public List<Long> findWorkIdsByUserId(Long userId) {
        return daoClient.invoke(moduleName, "findWorkIdsByUserId",
                new Object[]{userId});
    }

    @Override
    public Pager<Work> findAllWorkByConditionPager(Work work, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllWorkByConditionPager",
                new Object[]{work, firstResult, sizeNo});
    }
}
