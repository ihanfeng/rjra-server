package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.WorkExperience;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface WorkExperienceService extends Serializable {
    public List<WorkExperience> findAllWorkExperience();
}
