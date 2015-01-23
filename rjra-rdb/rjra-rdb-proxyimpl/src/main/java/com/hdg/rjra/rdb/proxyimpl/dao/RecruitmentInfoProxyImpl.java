package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IRecruitmentInfoProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.RecruitmentInfo;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class RecruitmentInfoProxyImpl implements IRecruitmentInfoProxy {

    private static String moduleName = "rdb-recruitment-info";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public List<RecruitmentInfo> findAllRecruitmentInfoByCompanyId(Long companyId, Integer[] status) {
        return daoClient.invoke(moduleName, "findAllRecruitmentInfoByCompanyId",
                new Object[]{companyId, status});
    }

    @Override
    public Pager<RecruitmentInfo> findAllRecruitmentInfoByCompanyIdPager(Long companyId, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllRecruitmentInfoByCompanyIdPager",
                new Object[] { companyId, status, firstResult, sizeNo });
    }

    @Override
    public RecruitmentInfo findRecruitmentInfoByRecruitmentInfoId(Long recruitmentInfoId) {
        return daoClient.invoke(moduleName, "findRecruitmentInfoByRecruitmentInfoId",
                new Object[]{recruitmentInfoId});
    }

    @Override
    public List<RecruitmentInfo> findAllRecruitmentInfo(Integer[] status) {
        return daoClient.invoke(moduleName, "findAllRecruitmentInfo",
                new Object[]{status});
    }

    @Override
    public Pager<RecruitmentInfo> findAllRecruitmentInfoPager(Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllRecruitmentInfoPager",
                new Object[] { status, firstResult, sizeNo });
    }

    @Override
    public Integer updateRecruitmentInfoStatus(Long recruitmentInfoId, Integer status) {
        return daoClient.invoke(moduleName, "updateRecruitmentInfoStatus",
                new Object[]{recruitmentInfoId, status});
    }

    @Override
    public Long saveRecruitmentInfo(RecruitmentInfo recruitmentInfo) {
        return daoClient.invoke(moduleName, "saveRecruitmentInfo",
                new Object[]{recruitmentInfo});
    }

    @Override
    public Integer updateRecruitmentInfo(RecruitmentInfo recruitmentInfo) {
        return daoClient.invoke(moduleName, "updateRecruitmentInfo",
                new Object[]{recruitmentInfo});
    }

    @Override
    public Pager<RecruitmentInfo> findNearRecruitmentInfoByLocationPager(Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findNearRecruitmentInfoByLocationPager",
                new Object[]{lng, lat, raidus, status, firstResult, sizeNo});
    }
}
