package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.domain.WorkExperience;
import com.hdg.rjra.server.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    WorkExperienceService workExperienceService;

    @Override
    public List<WorkExperience> findAllWorkExperience() {
        return workExperienceService.findAllWorkExperience();
    }
}
