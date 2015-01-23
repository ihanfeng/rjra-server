package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Company;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class CompanyProxyImpl implements ICompanyProxy {

    private static String moduleName = "rdb-company";

    public Client daoClient;

    public Client getDaoClient() {
        return daoClient;
    }

    public void setDaoClient(Client daoClient) {
        this.daoClient = daoClient;
    }

    @Override
    public Long saveCompany(String mobile, String pwd) {
        return daoClient.invoke(moduleName, "saveCompany",
                new Object[]{mobile, pwd});
    }

    @Override
    public Integer updateCompany(Company company) {
        return daoClient.invoke(moduleName, "updateCompany",
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
    public Company findCompanyByMobileAndPwd(String mobile, String pwd) {
        return daoClient.invoke(moduleName, "findCompanyByMobileAndPwd",
                new Object[]{mobile, pwd});
    }

    @Override
    public Integer findCompanyExistsByMobile(String mobile) {
        return daoClient.invoke(moduleName, "findCompanyExistsByMobile",
                new Object[]{mobile});
    }
}
