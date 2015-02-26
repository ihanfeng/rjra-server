package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.company.CompanyBo;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface CompanyService {

    public Long createCompany();

    public Integer updateCompany(CompanyBo company);

    public CompanyBo findCompanyByCompanyId(Long companyId);

    public Pager<CompanyBo> findAllCompanyPager(Integer firstResult, Integer sizeNo);

    public Integer updateCompanyBizlicense(Long companyId, Long fileId);

    public Integer updateCompanyLogo(Long companyId, Long fileId);

    public Integer updateCompanyUserIdCard(Long companyId, Long fileId);

    public Integer updateCompanyFacade(Long companyId, Long fileId);
}
