package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Resume;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IResumeProxy extends Serializable {

    public Long createResume(String mobile);

    public Resume findResumeByResumeId(Long resumeId);

    public Integer updatResumeStatus(Long resumeId, Integer status);

    public Integer updateResume(Resume resume);
}
