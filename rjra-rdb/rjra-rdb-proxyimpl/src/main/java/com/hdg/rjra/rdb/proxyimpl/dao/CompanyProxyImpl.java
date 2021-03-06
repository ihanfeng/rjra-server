package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyProxy;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.Pager;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class CompanyProxyImpl extends BaseProxy implements ICompanyProxy {

    private static String moduleName = "rdb-company";

    @Override
    public Long createCompany() {
        return daoClient.invoke(moduleName, "createCompany",
                new Object[]{});
    }

    @Override
    public Long saveCompany(Company company) {
        return daoClient.invoke(moduleName, "saveCompany",
                new Object[]{company});
    }

    @Override
    public List<Long> batchSaveCompany(List<Company> companyList) {
        return daoClient.invoke(moduleName, "batchSaveCompany",
                new Object[]{companyList});
    }

    @Override
    public Integer deleteCompany(List<Long> companyIds) {
        return daoClient.invoke(moduleName, "deleteCompany",
                new Object[]{companyIds});
    }

    @Override
    public Integer updateCompany(Company company) {
        return daoClient.invoke("rdb-common", "updateByPk",
                new Object[]{company});
    }

    @Override
    public Company findCompanyByCompanyId(Long companyId) {
        return daoClient.invoke(moduleName, "findCompanyByCompanyId",
                new Object[]{companyId});
    }

    @Override
    public Pager<Company> findAllCompanyPager(Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllCompanyPager",
                new Object[]{status, firstResult, sizeNo});
    }

    @Override
    public Pager<Company> findAllCompanyByConditionPager(Company company, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllCompanyByConditionPager",
                new Object[]{company, firstResult, sizeNo});
    }

    @Override
    public Integer updateCompanyBizlicense(Long companyId, Long fileId) {
        return daoClient.invoke(moduleName, "updateCompanyBizlicense",
                new Object[]{companyId, fileId});
    }

    @Override
    public Integer updateCompanyLogo(Long companyId, Long fileId) {
        return daoClient.invoke(moduleName, "updateCompanyLogo",
                new Object[]{companyId, fileId});
    }

    @Override
    public Integer updateCompanyUserIdCard(Long companyId, Long fileId) {
        return daoClient.invoke(moduleName, "updateCompanyUserIdCard",
                new Object[]{companyId, fileId});
    }

    @Override
    public Integer updateCompanyFacade(Long companyId, Long fileId) {
        return daoClient.invoke(moduleName, "updateCompanyFacade",
                new Object[]{companyId, fileId});
    }

    @Override
    public Integer updateCompanyImages(Long companyId, Long[] imageIds) {
        return daoClient.invoke(moduleName, "updateCompanyImages",
                new Object[]{companyId, imageIds});
    }
}
