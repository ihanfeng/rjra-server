package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.IWorkExperienceProxy;
import com.hdg.rjra.rdb.proxy.domain.WorkExperience;
import com.hdg.rjra.server.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    IWorkExperienceProxy workExperienceProxy;

    @Override
    public List<WorkExperience> findAllWorkExperience() {
        return workExperienceProxy.findAllWorkExperience();
    }
}
