package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.IEducationProxy;
import com.hdg.rjra.rdb.proxy.domain.Education;
import com.hdg.rjra.server.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    IEducationProxy educationProxy;

    @Override
    public List<Education> findAllEducation() {
        return educationProxy.findAllEducation();
    }
}
