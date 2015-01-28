package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IResumeProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.rdb.proxy.domain.enumerate.ResumeMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class ResumeProxyImpl extends BaseProxy implements IResumeProxy {

    private static String moduleName = "rdb-resume";

    @Override
    public Long createResume(String mobile) {
        return daoClient.invoke(moduleName, "createResume",
                new Object[]{mobile});
    }

    @Override
    public Resume findResumeByResumeId(Long resumeId) {
        return daoClient.invoke(moduleName, "findResumeByResumeId",
                new Object[]{resumeId});
    }

    @Override
    public Integer updatResumeStatus(Long resumeId, Integer status) {
        return daoClient.invoke(moduleName, "updatResumeStatus",
                new Object[]{resumeId, status});
    }

    @Override
    public Integer updateResume(Resume resume) {
        return daoClient.invoke(moduleName, "updateResume",
                new Object[]{resume});
    }

    @Override
    public Integer updateResumeHead(Long resumeId, Long fileId) {
        return daoClient.invoke(moduleName, "updateResumeHead",
                new Object[]{resumeId, fileId});
    }

    @Override
    public Pager<Resume> findAllResumeByParamPager(Map<ResumeMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllResumeByParamPager",
                new Object[]{param, status, firstResult, sizeNo});
    }

    @Override
    public Pager<Resume> findNearResumeByParamPager(Map<ResumeMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findNearResumeByParamPager",
                new Object[]{param, lng, lat, raidus, status, firstResult, sizeNo});
    }
}
