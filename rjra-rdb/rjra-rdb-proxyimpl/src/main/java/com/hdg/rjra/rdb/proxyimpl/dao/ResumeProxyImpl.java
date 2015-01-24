package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IResumeProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Resume;

import java.util.List;

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
}
