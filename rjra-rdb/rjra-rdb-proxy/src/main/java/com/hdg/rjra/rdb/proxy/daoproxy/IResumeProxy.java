package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.rdb.proxy.domain.enumerate.ResumeMapping;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IResumeProxy extends Serializable {

    public Long createResume(String mobile);

    public Resume findResumeByResumeId(Long resumeId);

    public Integer updatResumeStatus(Long resumeId, Integer status);

    public Integer updateResume(Resume resume);

    public Integer updateResumeHead(Long resumeId, Long fileId);

    public Pager<Resume> findAllResumeByParamPager(Map<ResumeMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo);

    public Pager<Resume> findNearResumeByParamPager(Map<ResumeMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo);
}
