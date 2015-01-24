package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.resume.ResumeBo;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface ResumeService {

    public Long createResume(String mobile);

    public ResumeBo findResumeByResumeId(Long resumeId);

    public Integer updatResumeStatus(Long resumeId, Integer status);

    public Integer updateResume(ResumeBo resumeBo);
}
