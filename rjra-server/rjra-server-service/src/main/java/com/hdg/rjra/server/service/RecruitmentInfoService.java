package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.recruitmentinfo.RecruitmentInfoBo;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface RecruitmentInfoService {

    public List<RecruitmentInfoBo> findAllRecruitmentInfoByCompanyId(Long companyId);

    public Pager<RecruitmentInfoBo> findAllRecruitmentInfoByCompanyIdPager(Long companyId, Integer status, Integer firstResult,Integer sizeNo);

    public RecruitmentInfoBo findRecruitmentInfoByRecruitmentInfoId(Long recruitmentInfoId);

    public List<RecruitmentInfoBo> findAllRecruitmentInfo();

    public Pager<RecruitmentInfoBo> findAllRecruitmentInfoPager(Integer status, Integer firstResult,Integer sizeNo);

    public Integer updateRecruitmentInfoStatus(Long recruitmentInfoId, Integer status);

    public Long saveRecruitmentInfo(RecruitmentInfoBo recruitmentInfo);

    public Integer updateRecruitmentInfo(RecruitmentInfoBo recruitmentInfo);

    public Pager<RecruitmentInfoBo> findNearRecruitmentInfoByLocationPager(Double lng, Double lat, Integer raidus, Integer firstResult,Integer sizeNo);

}
