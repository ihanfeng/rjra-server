package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.Pager;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICompanyProxy extends Serializable {

    public Long createCompany();

    public Integer updateCompany(Company company);

    public Company findCompanyByCompanyId(Long companyId);

    public Pager<Company> findAllCompanyPager(Integer[] status, Integer firstResult, Integer sizeNo);

    public Pager<Company> findAllCompanyByConditionPager(Company company, Integer firstResult, Integer sizeNo);

    public Integer updateCompanyBizlicense(Long companyId, Long fileId);

    public Integer updateCompanyLogo(Long companyId, Long fileId);

    public Integer updateCompanyUserIdCard(Long companyId, Long fileId);

    public Integer updateCompanyFacade(Long companyId, Long fileId);

    public Integer updateCompanyImages(Long companyId, Long[] imageIds);

}
