package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.CompanyScale;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface CompanyScaleService extends Serializable {
    public List<CompanyScale> findAllCompanyScale();
}
