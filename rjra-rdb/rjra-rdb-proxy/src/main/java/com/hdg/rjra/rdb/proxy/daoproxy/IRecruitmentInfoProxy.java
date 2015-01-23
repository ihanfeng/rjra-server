package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.RecruitmentInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IRecruitmentInfoProxy extends Serializable {

    public List<RecruitmentInfo> findAllRecruitmentInfoByCompanyId(Long companyId, Integer[] status);

    public Pager<RecruitmentInfo> findAllRecruitmentInfoByCompanyIdPager(Long companyId, Integer[] status, Integer firstResult,Integer sizeNo);

    public RecruitmentInfo findRecruitmentInfoByRecruitmentInfoId(Long recruitmentInfoId);

    public List<RecruitmentInfo> findAllRecruitmentInfo(Integer[] status);

    public Pager<RecruitmentInfo> findAllRecruitmentInfoPager(Integer[] status, Integer firstResult,Integer sizeNo);

    public Integer updateRecruitmentInfoStatus(Long recruitmentInfoId, Integer status);

    public Long saveRecruitmentInfo(RecruitmentInfo recruitmentInfo);

    public Integer updateRecruitmentInfo(RecruitmentInfo recruitmentInfo);

    public Pager<RecruitmentInfo> findNearRecruitmentInfoByLocationPager(Double lat, Double lng, Integer raidus, Integer[] status, Integer firstResult,Integer sizeNo);

}
