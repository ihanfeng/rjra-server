package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyTypeProxy;
import com.hdg.rjra.rdb.proxy.domain.CompanyType;
import com.hdg.rjra.server.service.CompanyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

    @Autowired
    ICompanyTypeProxy companyTypeProxy;

    @Override
    public List<CompanyType> findAllCompanyType() {
        return companyTypeProxy.findAllCompanyType();
    }
}
