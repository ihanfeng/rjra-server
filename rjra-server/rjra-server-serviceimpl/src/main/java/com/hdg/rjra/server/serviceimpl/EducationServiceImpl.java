package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.domain.Education;
import com.hdg.rjra.server.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationService educationService;

    @Override
    public List<Education> findAllEducation() {
        return educationService.findAllEducation();
    }
}
