package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.ICustomerProxy;
import com.hdg.rjra.rdb.proxy.domain.Customer;

/**
 * Created by Rock on 2015/3/9 0009.
 */
public class CustomerProxyImpl extends BaseProxy implements ICustomerProxy {

    private static String moduleName = "rdb-customer";

    @Override
    public Customer findCustomerByNameAndPwd(String name, String pwd) {

        return daoClient.invoke(moduleName, "findCustomerByNameAndPwd",
                new Object[]{name, pwd});
    }

    @Override
    public Integer updateCustomerPwd(Long customerId, String pwd) {

        return daoClient.invoke(moduleName, "updateCustomerPwd",
                new Object[]{customerId, pwd});
    }
}