package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.domain.CompanyScale;
import com.hdg.rjra.server.service.CompanyScaleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class CompanyScaleServiceImpl implements CompanyScaleService {

    @Autowired
    CompanyScaleService companyScaleService;

    @Override
    public List<CompanyScale> findAllCompanyScale() {
        return companyScaleService.findAllCompanyScale();
    }
}
