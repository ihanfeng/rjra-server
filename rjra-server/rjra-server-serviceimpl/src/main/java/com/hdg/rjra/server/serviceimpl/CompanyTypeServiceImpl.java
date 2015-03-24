package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.domain.CompanyType;
import com.hdg.rjra.server.service.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class CompanyTypeServiceImpl implements CompanyTypeService {

    @Autowired
    CompanyTypeService companyTypeService;

    @Override
    public List<CompanyType> findAllCompanyType() {
        return companyTypeService.findAllCompanyType();
    }
}
