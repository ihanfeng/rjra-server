package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.enumerate.ResumeMapping;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;

import java.util.Map;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface ResumeService {

    public Long createResume(String mobile);

    public ResumeBo findResumeByResumeId(Long resumeId);

    public Integer updatResumeStatus(Long resumeId, Integer status);

    public Integer updateResume(ResumeBo resumeBo);

    public Integer updateResumeHead(Long resumeId, Long fileId);

    public Pager<ResumeBo> findAllResumeByParamPager(Map<ResumeMapping, Object> param, Integer firstResult, Integer sizeNo);

    public Pager<ResumeBo> findNearResumeByParamPager(Map<ResumeMapping, Object> param, Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo);

}
