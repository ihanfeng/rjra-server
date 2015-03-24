package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.CompanyScale;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICompanyScaleProxy extends Serializable {

    public List<CompanyScale> findAllCompanyScale();
}
