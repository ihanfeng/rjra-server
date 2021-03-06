package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyScaleProxy;
import com.hdg.rjra.rdb.proxy.domain.CompanyScale;
import com.hdg.rjra.server.service.CompanyScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class CompanyScaleServiceImpl implements CompanyScaleService {

    @Autowired
    ICompanyScaleProxy companyScaleProxy;

    @Override
    public List<CompanyScale> findAllCompanyScale() {
        return companyScaleProxy.findAllCompanyScale();
    }
}
